package com.space.core.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.space.core.data.local.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
}