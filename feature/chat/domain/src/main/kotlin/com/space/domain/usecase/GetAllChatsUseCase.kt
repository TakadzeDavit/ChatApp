package com.space.domain.usecase

import com.space.domain.model.ChatResponse
import com.space.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow


class GetAllChatsUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(userId: String): Flow<List<ChatResponse>> = repository.getAllChats(userId)
}