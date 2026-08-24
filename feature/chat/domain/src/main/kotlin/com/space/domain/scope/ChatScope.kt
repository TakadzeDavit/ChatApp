package com.space.domain.scope

sealed interface ChatScope {
    object ParentScope : ChatScope
    object ChatListScope : ChatScope
    object AddChatScope : ChatScope
    object ChatDetailScope : ChatScope
}