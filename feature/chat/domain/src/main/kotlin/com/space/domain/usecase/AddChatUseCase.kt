package com.space.domain.usecase

import com.space.domain.model.ChatResponse
import com.space.domain.repository.ChatRepository
import common.ApiResult


class AddChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(newChat: ChatResponse): ApiResult<Unit> =
        repository.addChat(newChat)
}