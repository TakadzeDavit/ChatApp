package com.space.presentation.chat.flow.chat_list.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.space.presentation.BaseScreen
import com.space.presentation.chat.flow.chat_list.contract.ChatListEvent
import com.space.presentation.chat.flow.chat_list.contract.ChatListState
import com.space.presentation.chat.flow.chat_list.vm.ChatListVm
import com.space.ui.component.ChatItem
import com.space.ui.utils.toFormattedTime
import org.koin.core.parameter.parametersOf


@Composable
fun ChatListScreen() {
    BaseScreen(
        vmClass = ChatListVm::class,
        parameters = { parametersOf("test-user-123") }
    ) { state, onEvent ->
        ChatListScreenContent(state = state, onEvent = onEvent)
    }
}

@Composable
private fun ChatListScreenContent(
    state: ChatListState,
    onEvent: (ChatListEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                if(state.chats.isEmpty()) {
                    Text(
                        text = "No chats found",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = state.chats,
                        key = { it.chatId }
                    ) { item ->
                        ChatItem(
                            title = item.contactName,
                            lastMessageTime = item.lastMessageTime?.toFormattedTime(),
                            lastMessage = item.lastMessage,
                            onClick = { onEvent(ChatListEvent.OnChatClicked(item.chatId)) }
                        )
                    }
                }
            }
        }
    }
}