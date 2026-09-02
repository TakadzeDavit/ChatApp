package com.space.presentation.chat.flow.chat_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.space.chat.presentation.R
import com.space.presentation.BaseScreen
import com.space.presentation.chat.flow.chat_list.contract.ChatListEvent
import com.space.presentation.chat.flow.chat_list.contract.ChatListState
import com.space.presentation.chat.flow.chat_list.vm.ChatListVm
import com.space.ui.component.AddButton
import com.space.ui.component.ChatItem
import com.space.ui.component.EmptyList
import com.space.ui.component.SwipeToDeleteChatItem
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Padding
import com.space.ui.theme.TextSizing
import com.space.ui.utils.toFormattedTime
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf


object ChatScreen : BaseScreen<ChatListState, ChatListEvent>() {
    override val vmClass = ChatListVm::class
    override val parameters: ParametersDefinition = { parametersOf("test-user-123") }

    @Composable
    override fun Content(
        state: ChatListState,
        onEvent: (ChatListEvent) -> Unit
    ) {
        ChatListScreenContent(state, onEvent)
    }
}

@Composable
private fun ChatListScreenContent(
    state: ChatListState,
    onEvent: (ChatListEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Spacer(Modifier.height(Padding.chatListScreenTopPadding))
        Text(
            text = stringResource(R.string.chats),
            color = colors.textPrimary,
            fontSize = TextSizing.size30,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = Padding.chatTitlePaddingH,
                vertical = Padding.chatTitlePaddingV
            )
        )
        Spacer(Modifier.height(Padding.chatListTopPadding))
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
                    if (state.chats.isEmpty()) {
                        EmptyList(
                            modifier = Modifier.align(Alignment.Center),
                            primaryText = stringResource(R.string.no_chats),
                            secondaryText = stringResource(R.string.start_chat)
                        )
                    }
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            items = state.chats,
                            key = { _, chat -> chat.chatId }
                        ) { index, chat ->
                            SwipeToDeleteChatItem(
                                onDelete = { onEvent(ChatListEvent.OnChatDeleted(chat.chatId)) }
                            ) {
                                ChatItem(
                                    title = chat.contactName,
                                    lastMessageTime = chat.lastMessageTime?.toFormattedTime(),
                                    lastMessage = chat.lastMessage,
                                    onClick = { onEvent(ChatListEvent.OnChatClicked(chat.chatId)) },
                                )
                            }
                            if (index < state.chats.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = Padding.chatListPadding),
                                    color = colors.textPrimary.copy(alpha = 0.1f)
                                )
                            }
                        }
                    }
                }
            }
            AddButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Padding.addButtonPadding),
                onClick = { onEvent(ChatListEvent.OnAddChatClicked) }
            )
        }
    }
}