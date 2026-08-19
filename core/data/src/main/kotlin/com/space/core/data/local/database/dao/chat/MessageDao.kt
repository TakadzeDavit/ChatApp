package com.space.core.data.local.database.dao.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.core.data.local.database.entity.chat.MessageEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDao {

    @Insert
    suspend fun sentMessage(message: MessageEntity)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timeStamp ASC")
    fun getAllMessages(chatId: String): Flow<List<MessageEntity>>
}