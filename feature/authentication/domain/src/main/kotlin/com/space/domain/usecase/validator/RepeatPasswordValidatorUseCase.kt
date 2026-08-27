package com.space.domain.usecase.validator

class RepeatPasswordValidatorUseCase {
    operator fun invoke(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}