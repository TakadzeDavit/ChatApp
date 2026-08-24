package com.space.domain.usecase.validator

class EmptyFieldsValidatorUseCase {
    operator fun invoke(fields: List<String>): Boolean = fields.any { it.isBlank() }
}