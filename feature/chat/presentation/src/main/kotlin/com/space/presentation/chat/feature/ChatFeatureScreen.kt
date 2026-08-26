package com.space.presentation.chat.feature

import androidx.compose.runtime.Composable
import com.space.domain.scope.ChatScope
import com.space.presentation.FlowContainer
import com.space.presentation.navigator.ChatListScreenKey
import com.space.presentation.navigator.chatFlowEntry
import org.koin.compose.scope.KoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ChatFeatureScreen() {
    KoinScope(
        scopeDefinition = {
            getOrCreateScope(
                scopeId = named<ChatScope.ParentScope>().value,
                qualifier = named<ChatScope.ParentScope>()
            )
        }
    ) {
        FlowContainer(
            initialKey = ChatListScreenKey("test-user-123"),
            entry = {
                chatFlowEntry()
            }
        )
    }
}