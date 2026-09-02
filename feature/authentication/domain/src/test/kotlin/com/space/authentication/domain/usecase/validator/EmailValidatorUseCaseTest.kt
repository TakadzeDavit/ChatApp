package com.space.authentication.domain.usecase.validator

import com.google.common.truth.Truth.assertThat
import com.space.domain.usecase.validator.EmailValidatorUseCase
import org.junit.Test

class EmailValidatorUseCaseTest {

    private val emailValidatorUseCase = EmailValidatorUseCase()

    @Test
    fun `given valid email addresses, when invoke is called, then returns true`() {
        val validEmails = listOf(
            "test@example.com",
            "user.name+tag@domain.co.uk",
            "user123@sub.domain.org"
        )

        validEmails.forEach { email ->
            val result = emailValidatorUseCase(email)
            assertThat(result).isTrue()
        }
    }

    @Test
    fun `given invalid email addresses, when invoke is called, then returns false`() {
        val invalidEmails = listOf(
            "",
            "   ",
            "invalid-email",
            "@domain.com",
            "user@.com",
            "user@domain"
        )

        invalidEmails.forEach { email ->
            val result = emailValidatorUseCase(email)
            assertThat(result).isFalse()
        }
    }
}