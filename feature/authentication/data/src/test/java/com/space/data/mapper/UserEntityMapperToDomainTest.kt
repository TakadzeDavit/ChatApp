package com.space.data.mapper

import com.google.common.truth.Truth.assertThat
import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.domain.model.UserModel
import org.junit.Test

class UserEntityMapperToDomainTest {
    private var mapper = UserEntityMapperToDomain()

    @Test
    fun `given UserEntity, when map is called, then successfully transforms into UserModel`() {
        // given
        val userEntity = UserEntity(
            id = "12",
            name = "Jemala",
            email = "jemala@gmail.com",
            password = "androidiMagaria123"
        )

        // when
        val actualModel = mapper.map(userEntity)

        // then
        val userModel = UserModel(
            id = "12",
            name = "Jemala",
            email = "jemala@gmail.com",
            password = "androidiMagaria123"
        )
        assertThat(actualModel).isEqualTo(userModel)
    }
}