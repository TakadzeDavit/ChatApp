package com.space.domain.repository

import com.space.core.domain.common.ApiResult
import com.space.domain.model.UserModel

interface LoginRepository {
    suspend fun login(
        email: String,
        password: String
    ) : ApiResult<UserModel>
}