package com.space.domain.usecase

import com.space.domain.model.MessageResponse
import com.space.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow


class GetAllMessagesUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(chatId: String): Flow<List<MessageResponse>> =
        repository.getAllMessages(chatId)
}