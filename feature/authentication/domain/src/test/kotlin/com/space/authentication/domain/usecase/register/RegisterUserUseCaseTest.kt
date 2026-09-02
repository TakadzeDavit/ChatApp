package com.space.authentication.domain.usecase.register

import com.google.common.truth.Truth
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.domain.repository.RegisterRepository
import com.space.domain.usecase.register.RegisterUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RegisterUserUseCaseTest {
    private val registerRepository: RegisterRepository = mockk()
    private lateinit var registerUserUseCase: RegisterUserUseCase

    @Before
    fun setUp() {
        registerUserUseCase = RegisterUserUseCase(registerRepository)
    }

    private data class TestCredentials(
        val name: String = "Jemala",
        val email: String = "jemala@gmail.com",
        val password: String = "Password123"
    )

    @Test
    fun `given valid user data, when invoke is called, then delegates to repository and returns success`() =
        runTest {
            // given
            val credentials = TestCredentials()
            val expectedResult = ApiResult.Success(Unit)

            coEvery {
                registerRepository.register(
                    name = credentials.name,
                    password = credentials.password,
                    email = credentials.email
                )
            } returns expectedResult

            // when
            val actualResult = registerUserUseCase(
                name = credentials.name,
                password = credentials.password,
                email = credentials.email
            )

            // then
            Truth.assertThat(actualResult).isEqualTo(expectedResult)
            coVerify(exactly = 1) {
                registerRepository.register(
                    name = credentials.name,
                    password = credentials.password,
                    email = credentials.email
                )
            }
        }

    @Test
    fun `given repository fails, when invoke is called, then returns error`() =
        runTest {
            // given
            val credentials = TestCredentials(email = "existing@gmail.com")
            val expectedError = ApiResult.Error(NetworkError.USER_ALREADY_EXISTS)

            coEvery {
                registerRepository.register(
                    name = credentials.name,
                    password = credentials.password,
                    email = credentials.email
                )
            } returns expectedError

            // when
            val actualResult = registerUserUseCase(
                name = credentials.name,
                password = credentials.password,
                email = credentials.email
            )

            // then
            Truth.assertThat(actualResult).isEqualTo(expectedError)
            coVerify(exactly = 1) {
                registerRepository.register(
                    name = credentials.name,
                    password = credentials.password,
                    email = credentials.email
                )
            }
        }
}