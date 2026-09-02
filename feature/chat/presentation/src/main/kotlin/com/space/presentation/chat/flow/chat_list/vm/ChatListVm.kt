package com.space.presentation.chat.flow.chat_list.vm

import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.DeleteChatUseCase
import com.space.domain.usecase.GetAllChatsUseCase
import com.space.presentation.BaseVm
import com.space.presentation.chat.flow.chat_list.contract.ChatListEvent
import com.space.presentation.chat.flow.chat_list.contract.ChatListState
import com.space.presentation.navigator.ChatDetailsScreenKey
import common.ApiResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ChatListVm(
    private val deleteChatUseCase: DeleteChatUseCase,
    private val getAllChatsUseCase: GetAllChatsUseCase,
    private val userId: String = "test-user-123"
) : BaseVm<ChatListState, ChatListEvent>(ChatListState()) {

    init {
        observeChats()
    }

    override fun onEvent(event: ChatListEvent) {
        when (event) {
            is ChatListEvent.OnAddChatClicked -> {}
            is ChatListEvent.OnChatDeleted -> deleteChat(event.chatId)
            is ChatListEvent.OnChatClicked -> {
                flowNavigator { push(ChatDetailsScreenKey("chat123")) }
            }
        }
    }

    private fun observeChats() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            getAllChatsUseCase(userId).catch { e ->
                updateState { copy(isLoading = false, errorMessage = e.message) }
            }.collect { chats ->
                updateState { copy(chats = chats, isLoading = false) }
            }
        }
    }
    private fun deleteChat(chatId: String) {
        viewModelScope.launch {
            when (val result = deleteChatUseCase(chatId)) {
                is ApiResult.Success -> {}
                is ApiResult.Error -> {
                    updateState { copy(errorMessage = result.message) }
                }
            }
        }
    }
}