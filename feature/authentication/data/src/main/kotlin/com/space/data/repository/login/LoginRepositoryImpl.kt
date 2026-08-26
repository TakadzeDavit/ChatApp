package com.space.data.repository.login

import com.space.core.data.local.database.dao.auth.UserDao
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.data.mapper.UserEntityMapperToDomain
import com.space.domain.model.UserModel
import com.space.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val userDao: UserDao,
    private val userEntityMapperToDomain: UserEntityMapperToDomain
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): ApiResult<UserModel> = try {
        val userEntity = userDao.getUserByEmailAndPassword(
            email = email,
            password = password
        )

        when (userEntity) {
            null -> ApiResult.Error(errorType = NetworkError.USER_NOT_FOUND)
            else -> ApiResult.Success(userEntityMapperToDomain.map(userEntity))
        }
    } catch (e: Exception) {
        ApiResult.Error(
            errorType = NetworkError.SOMETHING_WENT_WRONG,
            message = e.message
        )
    }
}