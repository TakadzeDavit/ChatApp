package com.space.authentication.domain.usecase.validator

import com.google.common.truth.Truth.assertThat
import com.space.domain.usecase.validator.RepeatPasswordValidatorUseCase
import org.junit.Test

class RepeatPasswordValidatorUseCaseTest {
    private val repeatPasswordValidatorUseCase = RepeatPasswordValidatorUseCase()

    @Test
    fun `given passwords match, when invoke is called, then returns true`() {
        val password = "Password123"
        val repeatPassword = "Password123"
        val result = repeatPasswordValidatorUseCase(password, repeatPassword)

        assertThat(result).isTrue()
    }

    @Test
    fun `given passwords do not match, when invoke is called, then returns false`() {
        val password = "Password123"
        val repeatPassword = "Password321"
        val result = repeatPasswordValidatorUseCase(password, repeatPassword)

        assertThat(result).isFalse()
    }
}