package com.space.data.mapper

import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.core.domain.common.BaseMapper
import com.space.domain.model.UserModel

class UserEntityMapperToDomain : BaseMapper<UserEntity, UserModel>{
    override fun map(input: UserEntity): UserModel {
        return UserModel(
            id = input.id,
            name = input.name,
            email = input.email,
            password = input.password
        )
    }
}