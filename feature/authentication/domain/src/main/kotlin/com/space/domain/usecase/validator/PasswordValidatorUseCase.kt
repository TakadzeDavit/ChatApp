package com.space.domain.usecase.validator

class PasswordValidatorUseCase {
    operator fun invoke(password: String): Boolean = password.length < 6
}