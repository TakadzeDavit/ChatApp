package com.space.data.repository.register

import com.space.core.data.local.database.dao.auth.UserDao
import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.domain.repository.RegisterRepository

class RegisterRepositoryImpl(
    private val userDao: UserDao
) : RegisterRepository {
    override suspend fun register(
        name: String,
        password: String,
        email: String
    ): ApiResult<Unit> = try {
        when (userDao.getUserByEmail(email)) {
            null -> {
                val user = UserEntity(
                    name = name,
                    email = email,
                    password = password
                )
                userDao.insertUser(user)
                ApiResult.Success(Unit)
            }

            else -> ApiResult.Error(errorType = NetworkError.USER_ALREADY_EXISTS)
        }
    } catch (e: Exception) {
        ApiResult.Error(
            errorType = NetworkError.SOMETHING_WENT_WRONG,
            message = e.message
        )
    }
}