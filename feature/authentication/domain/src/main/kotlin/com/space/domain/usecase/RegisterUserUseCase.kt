package com.space.domain.usecase

import com.space.domain.repository.RegisterRepository

class RegisterUserUseCase(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(
        name: String,
        password: String,
        email: String
    ) = registerRepository.register(
        name = name,
        password = password,
        email = email
    )
}