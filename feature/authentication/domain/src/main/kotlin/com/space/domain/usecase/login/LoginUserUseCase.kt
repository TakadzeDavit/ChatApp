package com.space.domain.usecase.login

import com.space.domain.repository.LoginRepository

class LoginUserUseCase(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        loginRepository.login(email = email, password = password)
}