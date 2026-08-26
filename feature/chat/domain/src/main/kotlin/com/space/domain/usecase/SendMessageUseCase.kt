package com.space.domain.usecase

import com.space.domain.model.MessageResponse
import com.space.domain.repository.ChatRepository


class SendMessageUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(newMessage: MessageResponse) = repository.sendMessage(newMessage)
}