package com.space.core.data.local.database.dao.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.core.data.local.database.entity.chat.ChatEntity
import kotlinx.coroutines.flow.Flow
import model.ChatListItem

@Dao
interface ChatDao {
    @Query(
        """
    SELECT 
        c.id as chatId, 
        c.contactName as contactName, 
        m.text as lastMessage, 
        m.timestamp as lastMessageTime
    FROM chats c
    LEFT JOIN messages m ON m.chatId = c.id
    WHERE c.ownerId = :userId
    AND (
        m.timestamp = (SELECT MAX(timestamp) FROM messages WHERE chatId = c.id) 
        OR m.id IS NULL
    )
    ORDER BY m.timestamp DESC, c.createdAt DESC
"""
    )
    fun getAllChats(userId: String): Flow<List<ChatListItem>>

    @Insert
    suspend fun addChat(newChat: ChatEntity)

    @Query("DELETE FROM chats WHERE id = :chatId")
    suspend fun deleteChat(chatId: String)
}