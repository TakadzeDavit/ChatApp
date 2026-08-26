package com.space.data.local.mapper

import com.space.core.domain.common.BaseMapper
import com.space.core.data.local.database.entity.chat.ChatEntity
import com.space.domain.model.ChatResponse

class ChatEntityMapperToResponse : BaseMapper<ChatEntity, ChatResponse> {
    override fun map(input: ChatEntity): ChatResponse {
        return ChatResponse(
            id = input.id,
            ownerId = input.ownerId,
            contactName = input.contactName,
            createdAt = input.createdAt
        )
    }
}

class ChatResponseMapperToEntity : BaseMapper<ChatResponse, ChatEntity> {
    override fun map(input: ChatResponse): ChatEntity {
        return ChatEntity(
            id = input.id,
            ownerId = input.ownerId,
            contactName = input.contactName,
            createdAt = input.createdAt
        )
    }
}