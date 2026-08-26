package com.space.domain.repository

import com.space.domain.model.ChatResponse
import com.space.domain.model.MessageResponse
import kotlinx.coroutines.flow.Flow
import model.ChatListItem

interface ChatRepository {
    fun getAllChats(userId: String): Flow<List<ChatListItem>>

    suspend fun addChat(newChat: ChatResponse)

    suspend fun deleteChat(chatId: String)

    suspend fun sendMessage(newMessage : MessageResponse)

    fun getAllMessages(chatId : String) : Flow<List<MessageResponse>>
}