package com.space.domain.usecase

import com.space.domain.model.ChatResponse
import com.space.domain.repository.ChatRepository


class AddChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(newChat: ChatResponse) = repository.addChat(newChat)
}