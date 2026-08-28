package com.space.feature.authentication.presentation.registration.vm

import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.space.authentication.presentation.R
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.core.testing.base.BaseViewModelTest
import com.space.domain.model.UserModel
import com.space.domain.usecase.login.LoginUserUseCase
import com.space.domain.usecase.validator.EmailValidatorUseCase
import com.space.domain.usecase.validator.PasswordValidatorUseCase
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginEvent
import com.space.feature.authentication.presentation.auth.flow.login.vm.LoginVm
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class)
class LoginVmTest : BaseViewModelTest() {
    private val emailValidatorUseCase: EmailValidatorUseCase = mockk()
    private val passwordValidatorUseCase: PasswordValidatorUseCase = mockk()
    private val loginUserUseCase: LoginUserUseCase = mockk()

    private lateinit var viewModel: LoginVm

    override fun setUp() {
        super.setUp()

        viewModel = LoginVm(
            emailValidatorUseCase = emailValidatorUseCase,
            passwordValidatorUseCase = passwordValidatorUseCase,
            loginUserUseCase = loginUserUseCase
        )

        fillValidForm()
    }

    @Test
    fun `given any state, when OnThemeToggle is sent, then isDarkTheme becomes expected boolean`() =
        runTest {
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(LoginEvent.OnThemeToggle(isChecked = true))

                assertThat(awaitItem().isDarkTheme).isTrue()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given invalid email, when OnLoginClick is sent, then error is invalid_email and use case is not called`() =
        runTest {
            // given
            every { emailValidatorUseCase(any()) } returns false
            every { passwordValidatorUseCase(any()) } returns true

            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(LoginEvent.OnLoginClick)

                assertThat(awaitItem().error).isEqualTo(R.string.error_invalid_email)
                coVerify(exactly = 0) { loginUserUseCase(any(), any()) }

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given invalid password, when OnLoginClick is sent, then error is invalid_password and use case is not called`() =
        runTest {
            // given
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns false

            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(LoginEvent.OnLoginClick)

                assertThat(awaitItem().error).isEqualTo(R.string.error_invalid_password)
                coVerify(exactly = 0) { loginUserUseCase(any(), any()) }

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given valid form, when OnLoginClick is sent and api fails, then error is invalid_password_or_email`() =
        runTest {
            // given
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns true
            coEvery { loginUserUseCase(any(), any()) } coAnswers {
                delay(100.milliseconds)
                ApiResult.Error(NetworkError.USER_NOT_FOUND)
            }
            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(LoginEvent.OnLoginClick)

                // 1. Loading state validation
                val loadingState = awaitItem()
                assertThat(loadingState.isLoading).isTrue()
                assertThat(loadingState.error).isNull()

                // 2. Error result validation
                val resultState = awaitItem()
                assertThat(resultState.isLoading).isFalse()
                assertThat(resultState.error).isEqualTo(R.string.invalid_password_or_email)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given valid form, when OnLoginClick is sent and api succeeds, then emits loading then success`() =
        runTest {
            // GIVEN
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns true

            coEvery { loginUserUseCase(any(), any()) } coAnswers {
                delay(100.milliseconds)
                ApiResult.Success(
                    UserModel(
                        id = "123131",
                        name = "Giorgi",
                        email = "giorgi@example.com",
                        password = "Password123"
                    )
                )
            }

            // WHEN & THEN
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(LoginEvent.OnLoginClick)

                // loading state
                val loadingState = awaitItem()
                assertThat(loadingState.isLoading).isTrue()

                // success state
                val successState = awaitItem()
                assertThat(successState.isLoading).isFalse()
                assertThat(successState.error).isNull()

                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun fillValidForm(
        newEmail: String = "jemala@example.com",
        newPassword: String = "Password123"
    ) {
        with(viewModel.state.value) {
            email.setTextAndPlaceCursorAtEnd(newEmail)
            password.setTextAndPlaceCursorAtEnd(newPassword)
        }
    }
}