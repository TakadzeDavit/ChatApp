package com.space.presentation.chat.flow.chat_list.contract

import com.space.domain.model.ChatResponse
import com.space.presentation.UiEvent

sealed interface ChatListEvent : UiEvent {
    data class OnChatClicked(val chatId: String) : ChatListEvent
    data class OnChatDeleted(val chatId: String) : ChatListEvent
    data object OnAddChatClicked : ChatListEvent
}