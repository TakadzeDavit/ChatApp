package com.space.domain.usecase

import com.space.domain.model.ChatResponse
import com.space.domain.repository.ChatRepository


class DeleteChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chat: ChatResponse) = repository.deleteChat(chat)
}