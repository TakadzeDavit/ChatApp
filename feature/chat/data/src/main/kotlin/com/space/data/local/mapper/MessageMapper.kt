package com.space.data.local.mapper

import base.BaseMapper
import com.space.core.data.local.database.entity.chat.MessageEntity
import com.space.domain.model.MessageResponse

class MessageEntityMapperToResponse : BaseMapper<MessageEntity, MessageResponse> {
    override fun map(input: MessageEntity): MessageResponse {
        return MessageResponse(
            id = input.id,
            chatId = input.chatId,
            text = input.text,
            isSentByOwner = input.isSentByOwner,
            timeStamp = input.timeStamp
        )
    }
}

class MessageResponseMapperToEntity : BaseMapper<MessageResponse, MessageEntity> {
    override fun map(input: MessageResponse): MessageEntity {
        return MessageEntity(
            id = input.id,
            chatId = input.chatId,
            text = input.text,
            isSentByOwner = input.isSentByOwner,
            timeStamp = input.timeStamp,
        )
    }
}