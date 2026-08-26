package com.space.domain.repository

import com.space.domain.model.ChatResponse
import com.space.domain.model.MessageResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getAllChats(userId: String): Flow<List<ChatResponse>>

    suspend fun addChat(newChat: ChatResponse)

    suspend fun deleteChat(chat: ChatResponse)

    suspend fun sendMessage(newMessage : MessageResponse)

    fun getAllMessages(chatId : String) : Flow<List<MessageResponse>>
}