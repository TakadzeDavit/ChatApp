package com.space.data.local.repository

import com.space.core.data.local.database.dao.chat.ChatDao
import com.space.core.data.local.database.dao.chat.MessageDao
import com.space.data.local.mapper.ChatMapper
import com.space.data.local.mapper.MessageMapper
import com.space.domain.model.ChatResponse
import com.space.domain.model.MessageResponse
import com.space.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ChatRepositoryImpl(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
    private val chatMapper: ChatMapper,
    private val messageMapper: MessageMapper
) : ChatRepository {
    override fun getAllChats(userId: String): Flow<List<ChatResponse>> {
        return chatDao.getAllChats(userId)
            .map { entities -> entities.map { chatMapper.toDomain(it) } }
    }

    override suspend fun addChat(newChat: ChatResponse) {
        chatDao.addChat(chatMapper.toEntity(newChat))
    }

    override suspend fun deleteChat(chat: ChatResponse) {
        chatDao.deleteChat(chatMapper.toEntity(chat))
    }

    override suspend fun sendMessage(newMessage: MessageResponse) {
        messageDao.sendMessage(messageMapper.toEntity(newMessage))
    }

    override fun getAllMessages(chatId: String): Flow<List<MessageResponse>> {
        return messageDao.getAllMessages(chatId)
            .map { entities -> entities.map { messageMapper.toDomain(it) } }
    }
}