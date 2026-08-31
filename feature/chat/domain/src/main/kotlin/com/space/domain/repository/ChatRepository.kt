package com.space.domain.repository

import com.space.domain.model.ChatResponse
import com.space.domain.model.MessageResponse
import common.ApiResult
import kotlinx.coroutines.flow.Flow
import model.ChatListItem

interface ChatRepository {
    fun getAllChats(userId: String): Flow<List<ChatListItem>>

    suspend fun addChat(newChat: ChatResponse): ApiResult<Unit>

    suspend fun deleteChat(chatId: String): ApiResult<Unit>

    suspend fun sendMessage(newMessage: MessageResponse): ApiResult<Unit>

    fun getAllMessages(chatId: String): Flow<List<MessageResponse>>
}