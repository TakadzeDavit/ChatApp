package com.space.domain.usecase

import com.space.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import model.ChatListItem


class GetAllChatsUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(userId: String): Flow<List<ChatListItem>> = repository.getAllChats(userId)
}