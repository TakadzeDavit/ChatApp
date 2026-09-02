package com.space.data.repository

import com.google.common.truth.Truth.assertThat
import com.space.core.data.local.database.dao.auth.UserDao
import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.data.repository.register.RegisterRepositoryImpl
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RegisterRepositoryImplTest {
    private val userDao: UserDao = mockk()
    private lateinit var registerRepository: RegisterRepositoryImpl

    @Before
    fun setUp() {
        registerRepository = RegisterRepositoryImpl(userDao)
    }

    private data class TestCredentials(
        val name: String = "Jemala",
        val email: String = "jemala@gmail.com",
        val password: String = "Password123"
    )

    @Test
    fun `given user does not exist, when register is called, then inserts user and returns success`() =
        runTest {
            // given
            val credentials = TestCredentials()
            coEvery { userDao.getUserByEmail(credentials.email) } returns null
            coJustRun { userDao.insertUser(any()) }

            // when
            val actualResult = registerRepository.register(
                name = credentials.name,
                password = credentials.password,
                email = credentials.email
            )

            // then
            assertThat(actualResult).isEqualTo(ApiResult.Success(Unit))

            coVerify(exactly = 1) {
                userDao.insertUser(
                    match { entity ->
                        entity.name == credentials.name &&
                                entity.email == credentials.email &&
                                entity.password == credentials.password
                    }
                )
            }
        }

    @Test
    fun `given user already exists, when register is called, then returns USER_ALREADY_EXISTS error`() =
        runTest {
            // given
            val credentials = TestCredentials()
            val existingUser = UserEntity(
                id = "1234",
                name = credentials.name,
                email = credentials.email,
                password = credentials.password
            )

            coEvery { userDao.getUserByEmail(credentials.email) } returns existingUser

            // when
            val actualResult = registerRepository.register(
                name = credentials.name,
                password = credentials.password,
                email = credentials.email
            )

            // then
            assertThat(actualResult).isEqualTo(ApiResult.Error(NetworkError.USER_ALREADY_EXISTS))

            coVerify(exactly = 0) { userDao.insertUser(any()) }
        }

    @Test
    fun `given database throws exception, when register is called, then catches exception and returns SOMETHING_WENT_WRONG error`() =
        runTest {
            // given
            val credentials = TestCredentials()
            val errorMessage = "Database connection failed"

            coEvery { userDao.getUserByEmail(credentials.email) } throws RuntimeException(
                errorMessage
            )

            // when
            val actualResult = registerRepository.register(
                name = credentials.name,
                password = credentials.password,
                email = credentials.email
            )

            // then
            val expectedError = ApiResult.Error(
                errorType = NetworkError.SOMETHING_WENT_WRONG,
                message = errorMessage
            )
            assertThat(actualResult).isEqualTo(expectedError)
        }
}