package com.space.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.space.core.data.local.database.dao.auth.UserDao
import com.space.core.data.local.database.dao.chat.ChatDao
import com.space.core.data.local.database.dao.chat.MessageDao
import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.core.data.local.database.entity.chat.ChatEntity
import com.space.core.data.local.database.entity.chat.MessageEntity

@Database(
    entities = [UserEntity::class, ChatEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChatAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun chatDao(): ChatDao

    abstract fun messageDao(): MessageDao
}