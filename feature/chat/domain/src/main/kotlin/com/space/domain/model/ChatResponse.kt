package com.space.domain.model

data class ChatResponse(
    val id: String,
    val ownerId: String,
    val contactName: String,
    val createdAt: Long
)