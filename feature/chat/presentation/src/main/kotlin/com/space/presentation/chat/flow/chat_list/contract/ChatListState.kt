package com.space.presentation.chat.flow.chat_list.contract

import com.space.presentation.UiState
import model.ChatListItem

data class ChatListState(
    val chats: List<ChatListItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : UiState