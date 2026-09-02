package com.space.authentication.domain.usecase.validator

import com.google.common.truth.Truth.assertThat
import com.space.domain.usecase.validator.PasswordValidatorUseCase
import org.junit.Test

class PasswordValidatorUseCaseTest {
    private val passwordValidatorUseCase = PasswordValidatorUseCase()

    @Test
    fun `given password length is greater than 6, when invoke is called, then returns true`() {
        val validPassword = "Password123"
        val result = passwordValidatorUseCase(validPassword)
        assertThat(result).isTrue()
    }

    @Test
    fun `given password length is 6 or less, when invoke is called, then returns false`() {
        val shortPassword = "123456"
        val emptyPassword = ""

        assertThat(passwordValidatorUseCase(shortPassword)).isFalse()
        assertThat(passwordValidatorUseCase(emptyPassword)).isFalse()
    }
}