package com.space.core.domain.validator

class PasswordValidatorUseCase {
    operator fun invoke(password: String): Boolean = password.length < 6
}