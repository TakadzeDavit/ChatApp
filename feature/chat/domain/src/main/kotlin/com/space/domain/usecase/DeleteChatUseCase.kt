package com.space.domain.usecase

import com.space.domain.model.ChatResponse
import com.space.domain.repository.ChatRepository
import common.ApiResult


class DeleteChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chatId: String): ApiResult<Unit> = repository.deleteChat(chatId)
}