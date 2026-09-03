package com.space.data.local.mapper

import base.BaseMapper
import com.space.core.data.local.database.entity.chat.ChatEntity
import com.space.domain.model.ChatResponse

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