package com.space.core.domain.validator

class EmptyFieldsValidatorUseCase {
    operator fun invoke(fields: List<String>): Boolean = fields.any { it.isBlank() }
}