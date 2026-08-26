package com.space.presentation.navigator

import com.space.navigation.FlowNavigationKey
import kotlinx.serialization.Serializable

@Serializable
data class ChatListScreenKey(val userId: String) : FlowNavigationKey
@Serializable
data class ChatDetailsScreenKey(val chatId: String) : FlowNavigationKey
@Serializable
data object AddChatScreenKey : FlowNavigationKey