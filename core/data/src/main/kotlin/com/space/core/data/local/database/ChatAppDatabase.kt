package com.space.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.core.data.local.database.dao.UserDao
import com.space.core.data.local.database.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChatAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}