package com.space.data.local.repository

import com.space.core.data.local.database.dao.chat.ChatDao
import com.space.core.data.local.database.dao.chat.MessageDao
import com.space.data.local.mapper.ChatResponseMapperToEntity
import com.space.data.local.mapper.MessageEntityMapperToResponse
import com.space.data.local.mapper.MessageResponseMapperToEntity
import com.space.domain.model.ChatResponse
import com.space.domain.model.MessageResponse
import com.space.domain.repository.ChatRepository
import common.ApiResult
import common.NetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import model.ChatListItem

class ChatRepositoryImpl(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
    private val chatResponseMapperToEntity: ChatResponseMapperToEntity,
    private val messageEntityMapperToResponse: MessageEntityMapperToResponse,
    private val messageResponseMapperToEntity: MessageResponseMapperToEntity
) : ChatRepository {
    override fun getAllChats(userId: String): Flow<List<ChatListItem>> {
        return chatDao.getAllChats(userId)
    }

    override suspend fun addChat(newChat: ChatResponse): ApiResult<Unit> {
        return try {
            chatDao.addChat(chatResponseMapperToEntity.map(newChat))
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(NetworkError.SOMETHING_WENT_WRONG, e.message)
        }
    }

    override suspend fun deleteChat(chatId: String): ApiResult<Unit> {
        return try {
            chatDao.deleteChat(chatId)
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(NetworkError.SOMETHING_WENT_WRONG, e.message)
        }
    }

    override suspend fun sendMessage(newMessage: MessageResponse): ApiResult<Unit> {
        return try {
            messageDao.sendMessage(messageResponseMapperToEntity.map(newMessage))
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(NetworkError.SOMETHING_WENT_WRONG, e.message)
        }
    }

    override fun getAllMessages(chatId: String): Flow<List<MessageResponse>> {
        return messageDao.getAllMessages(chatId)
            .map { entities -> entities.map { messageEntityMapperToResponse.map(it) } }
    }
}