package com.space.authentication.domain.usecase.validator

import com.google.common.truth.Truth.assertThat
import com.space.domain.usecase.validator.EmptyFieldsValidatorUseCase
import org.junit.Test

class EmptyFieldsValidatorUseCaseTest {
    private val emptyFieldsValidatorUseCase = EmptyFieldsValidatorUseCase()

    @Test
    fun `given all fields are filled, when invoke is called, then returns false`() {
        val fields = listOf(
            "Giorgi",
            "giorgi@gmail.com",
            "Password123"
        )
        val result = emptyFieldsValidatorUseCase(fields)

        assertThat(result).isFalse()
    }

    @Test
    fun `given at least one field is blank, when invoke is called, then returns true`() {
        val fieldsWithBlank = listOf("Giorgi", "", "Password123")
        val fieldsWithSpaces = listOf("Giorgi", "   ", "Password123")

        assertThat(emptyFieldsValidatorUseCase(fieldsWithBlank)).isTrue()
        assertThat(emptyFieldsValidatorUseCase(fieldsWithSpaces)).isTrue()
    }

    @Test
    fun `given empty list of fields, when invoke is called, then returns false`() {
        val emptyList = emptyList<String>()
        val result = emptyFieldsValidatorUseCase(emptyList)

        assertThat(result).isFalse()
    }
}