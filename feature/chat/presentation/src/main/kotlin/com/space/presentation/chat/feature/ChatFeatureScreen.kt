package com.space.presentation.chat.feature

import androidx.compose.runtime.Composable
import com.space.presentation.FlowContainer
import com.space.presentation.navigator.ChatListScreenKey
import com.space.presentation.navigator.chatFlowEntry
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ChatFeatureScreen() {
    FlowContainer(
        initialKey = ChatListScreenKey("test-user-123"),
        entry = {
            chatFlowEntry()
        }
    )
}