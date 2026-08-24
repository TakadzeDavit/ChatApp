package com.space.data.local.mapper

import com.space.core.data.local.database.entity.chat.ChatEntity
import com.space.domain.model.ChatResponse

class ChatMapper {
    fun toDomain(chat: ChatEntity): ChatResponse {
        return ChatResponse(
            id = chat.id,
            ownerId = chat.ownerId,
            contactName = chat.contactName,
            createdAt = chat.createdAt
        )
    }

    fun toEntity(chat: ChatResponse): ChatEntity {
        return ChatEntity(
            id = chat.id,
            ownerId = chat.ownerId,
            contactName = chat.contactName,
            createdAt = chat.createdAt
        )
    }
}