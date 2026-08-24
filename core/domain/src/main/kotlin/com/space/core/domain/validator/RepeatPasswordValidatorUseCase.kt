package com.space.core.domain.validator

class RepeatPasswordValidatorUseCase {
    operator fun invoke(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}