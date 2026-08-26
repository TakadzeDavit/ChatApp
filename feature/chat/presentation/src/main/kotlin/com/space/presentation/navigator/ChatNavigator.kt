package com.space.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.domain.scope.ChatScope
import com.space.feature.authentication.api.ChatFeatureKey
import com.space.navigation.scopedEntry
import com.space.presentation.chat.feature.ChatFeatureScreen
import com.space.presentation.chat.flow.chat_details.ChatDetailsScreen
import com.space.presentation.chat.flow.chat_list.screen.ChatListScreen

fun EntryProviderScope<NavKey>.chatFeatureEntry() {
    entry<ChatFeatureKey> {
        ChatFeatureScreen()
    }
}

internal fun EntryProviderScope<NavKey>.chatFlowEntry() {
    scopedEntry<ChatListScreenKey, ChatScope.ChatListScope> {
        ChatListScreen()
    }
    scopedEntry<ChatDetailsScreenKey, ChatScope.ChatDetailScope> {
        ChatDetailsScreen()
    }
}