package com.space.core.data.local.database.dao.chat

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.space.core.data.local.database.entity.chat.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats WHERE ownerId = :userId")
    fun getAllChats(userId: String): Flow<List<ChatEntity>>

    @Insert
    suspend fun addChat(newChat: ChatEntity)

    @Delete
    suspend fun deleteChat(chat: ChatEntity)
}