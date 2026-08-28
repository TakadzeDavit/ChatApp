package com.space.authentication.domain.usecase.login

import com.google.common.truth.Truth
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.domain.model.UserModel
import com.space.domain.repository.LoginRepository
import com.space.domain.usecase.login.LoginUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LoginUserUseCaseTest {
    private val loginRepository: LoginRepository = mockk()
    private lateinit var loginUserUseCase: LoginUserUseCase

    @Before
    fun setUp() {
        loginUserUseCase = LoginUserUseCase(loginRepository)
    }

    private data class TestCredentials(
        val email: String = "giorgi@example.com",
        val password: String = "Password123"
    )

    @Test
    fun `given valid credentials, when invoke is called, then returns success from repository`() =
        runTest {
            // given
            val credentials = TestCredentials()
            val expectedUser = UserModel(
                id = "123",
                name = "Giorgi",
                email = credentials.email,
                password = credentials.password
            )
            val expectedResult = ApiResult.Success(expectedUser)

            coEvery {
                loginRepository.login(
                    email = credentials.email,
                    password = credentials.password
                )
            } returns expectedResult

            // when
            val actualResult = loginUserUseCase(
                email = credentials.email,
                password = credentials.password
            )

            // then
            Truth.assertThat(actualResult).isEqualTo(expectedResult)
            coVerify(exactly = 1) {
                loginRepository.login(
                    email = credentials.email,
                    password = credentials.password
                )
            }
        }

    @Test
    fun `given repository returns error, when invoke is called, then returns error`() =
        runTest {
            // given
            val credentials = TestCredentials(password = "wrong_password")
            val expectedError = ApiResult.Error(NetworkError.USER_NOT_FOUND)

            coEvery {
                loginRepository.login(
                    email = credentials.email,
                    password = credentials.password
                )
            } returns expectedError

            // when
            val actualResult = loginUserUseCase(
                email = credentials.email,
                password = credentials.password
            )

            // then
            Truth.assertThat(actualResult).isEqualTo(expectedError)
            coVerify(exactly = 1) {
                loginRepository.login(
                    email = credentials.email,
                    password = credentials.password
                )
            }
        }
}