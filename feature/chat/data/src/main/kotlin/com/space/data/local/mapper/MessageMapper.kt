package com.space.data.local.mapper

import com.space.core.data.local.database.entity.chat.MessageEntity
import com.space.domain.model.MessageResponse

class MessageMapper {
    fun toDomain(message : MessageEntity): MessageResponse {
        return MessageResponse(
            id = message.id,
            chatId = message.chatId,
            text = message.text,
            isSentByOwner = message.isSentByOwner,
            timeStamp = message.timeStamp
        )
    }

    fun toEntity(message : MessageResponse): MessageEntity {
        return MessageEntity(
            id = message.id,
            chatId = message.chatId,
            text = message.text,
            isSentByOwner = message.isSentByOwner,
            timeStamp = message.timeStamp
        )
    }
}