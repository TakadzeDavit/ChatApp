package com.space.domain.model

data class MessageResponse(
    val id: String,
    val chatId: String,
    val text: String,
    val isSentByOwner: Boolean,
    val timeStamp: Long
)