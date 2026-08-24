package com.space.domain.repository

import com.space.core.domain.common.ApiResult

interface RegisterRepository {
    suspend fun register(
        name: String,
        password: String,
        email: String
    ) : ApiResult<Unit>
}