package com.space.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.feature.authentication.api.ChatFeatureKey
import com.space.presentation.chat.feature.ChatFeatureScreen
import com.space.presentation.chat.flow.chat_list.screen.ChatScreen
import com.space.presentation.screen

fun EntryProviderScope<NavKey>.chatFeatureEntry() {
    entry<ChatFeatureKey> {
        ChatFeatureScreen()
    }
}

internal fun EntryProviderScope<NavKey>.chatFlowEntry() {
    screen<ChatListScreenKey> { ChatScreen }
}