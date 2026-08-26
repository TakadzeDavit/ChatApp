package model

data class ChatListItem(
    val chatId: String,
    val contactName: String,
    val lastMessage: String?,
    val lastMessageTime: Long?
)