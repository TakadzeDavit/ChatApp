package com.space.core.data.local.database.entity.chat

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.space.core.data.local.database.entity.auth.UserEntity
import java.util.UUID


@Entity(
    tableName = "chats",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ChatEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val ownerId: String,
    val contactName: String,
    val createdAt: Long = System.currentTimeMillis()
)