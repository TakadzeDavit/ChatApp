package com.space.domain.usecase

import com.space.domain.model.MessageResponse
import com.space.domain.repository.ChatRepository
import common.ApiResult


class SendMessageUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(newMessage: MessageResponse): ApiResult<Unit> =
        repository.sendMessage(newMessage)
}