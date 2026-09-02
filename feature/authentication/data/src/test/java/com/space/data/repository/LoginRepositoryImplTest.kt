package com.space.data.repository

import com.google.common.truth.Truth.assertThat
import com.space.core.data.local.database.dao.auth.UserDao
import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.data.mapper.UserEntityMapperToDomain
import com.space.data.repository.login.LoginRepositoryImpl
import com.space.domain.model.UserModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {
    private val userDao: UserDao = mockk()
    private val mapper: UserEntityMapperToDomain = mockk()
    private lateinit var loginRepository: LoginRepositoryImpl

    @Before
    fun setUp() {
        loginRepository = LoginRepositoryImpl(
            userDao = userDao,
            userEntityMapperToDomain = mapper
        )
    }

    private data class TestCredentials(
        val email: String = "giorgi@example.com",
        val password: String = "Password123"
    )

    @Test
    fun `given valid credentials, when login is called, then returns mapped user model in success result`() =
        runTest {
            // given
            val credentials = TestCredentials()
            val entity = UserEntity(
                id = "123",
                name = "Giorgi",
                email = credentials.email,
                password = credentials.password
            )
            val expectedUser = UserModel(
                id = "123",
                name = "Giorgi",
                email = credentials.email,
                password = credentials.password
            )

            coEvery {
                userDao.getUserByEmailAndPassword(
                    email = credentials.email,
                    password = credentials.password
                )
            } returns entity

            every { mapper.map(entity) } returns expectedUser

            // when
            val actualResult = loginRepository.login(
                email = credentials.email,
                password = credentials.password
            )

            // then
            assertThat(actualResult).isEqualTo(ApiResult.Success(expectedUser))

            coVerify(exactly = 1) {
                userDao.getUserByEmailAndPassword(
                    email = credentials.email,
                    password = credentials.password
                )
            }
            verify(exactly = 1) { mapper.map(entity) }
        }

    @Test
    fun `given invalid credentials, when login is called, then returns USER_NOT_FOUND error`() =
        runTest {
            // given
            val credentials = TestCredentials(password = "wrong_password")

            coEvery {
                userDao.getUserByEmailAndPassword(
                    email = credentials.email,
                    password = credentials.password
                )
            } returns null

            // when
            val actualResult = loginRepository.login(
                email = credentials.email,
                password = credentials.password
            )

            // then
            assertThat(actualResult).isEqualTo(
                ApiResult.Error(NetworkError.USER_NOT_FOUND)
            )
            verify(exactly = 0) { mapper.map(any()) }
        }

    @Test
    fun `given database throws exception, when login is called, then catches exception and returns SOMETHING_WENT_WRONG error`() =
        runTest {
            // given
            val credentials = TestCredentials()

            coEvery {
                userDao.getUserByEmailAndPassword(
                    email = credentials.email,
                    password = credentials.password
                )
            } throws RuntimeException()

            // when
            val actualResult = loginRepository.login(
                email = credentials.email,
                password = credentials.password
            )

            // then
            val expectedError = ApiResult.Error(
                errorType = NetworkError.SOMETHING_WENT_WRONG
            )
            assertThat(actualResult).isEqualTo(expectedError)
        }
}