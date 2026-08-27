package com.space.feature.authentication.presentation.registration.vm

import androidx.compose.foundation.text.input.TextFieldState
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.space.authentication.presentation.R
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.core.testing.base.BaseViewModelTest
import com.space.domain.usecase.register.RegisterUserUseCase
import com.space.domain.usecase.validator.EmailValidatorUseCase
import com.space.domain.usecase.validator.EmptyFieldsValidatorUseCase
import com.space.domain.usecase.validator.PasswordValidatorUseCase
import com.space.domain.usecase.validator.RepeatPasswordValidatorUseCase
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationEvent
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationState
import com.space.feature.authentication.presentation.auth.flow.registration.vm.RegistrationVm
import com.space.presentation.BaseVm
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegistrationVmTest : BaseViewModelTest() {
    private val emailValidatorUseCase: EmailValidatorUseCase = mockk()
    private val emptyFieldsValidatorUseCase: EmptyFieldsValidatorUseCase = mockk()
    private val passwordValidatorUseCase: PasswordValidatorUseCase = mockk()
    private val repeatPasswordValidatorUseCase: RepeatPasswordValidatorUseCase = mockk()
    private val registerUserUseCase: RegisterUserUseCase = mockk()
    private lateinit var viewModel: RegistrationVm

    override fun setUp() {
        super.setUp()

        viewModel = RegistrationVm(
            emailValidatorUseCase = emailValidatorUseCase,
            emptyFieldsValidatorUseCase = emptyFieldsValidatorUseCase,
            passwordValidatorUseCase = passwordValidatorUseCase,
            repeatPasswordValidatorUseCase = repeatPasswordValidatorUseCase,
            registerUserUseCase = registerUserUseCase
        )

        viewModel.forceState(
            RegistrationState(
                name = TextFieldState("Giorgi"),
                email = TextFieldState("giorgi@example.com"),
                password = TextFieldState("Password123"),
                repeatPassword = TextFieldState("Password123")
            )
        )
    }

    @Test
    fun `given any state, when OnThemeToggle true is sent, then isDarkTheme becomes true`() =
        runTest {
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(RegistrationEvent.OnThemeToggle(isChecked = true))

                assertThat(awaitItem().isDarkTheme).isTrue()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given dark theme enabled, when OnThemeToggle false is sent, then isDarkTheme becomes false`() =
        runTest {
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(RegistrationEvent.OnThemeToggle(isChecked = true))
                assertThat(awaitItem().isDarkTheme).isTrue()

                viewModel.onEvent(RegistrationEvent.OnThemeToggle(isChecked = false))
                assertThat(awaitItem().isDarkTheme).isFalse()

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given empty field in form, when OnRegisterClick is sent, then error is empty_fields and register use case is not called`() =
        runTest {
            // given
            every { emptyFieldsValidatorUseCase(any()) } returns true
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns false
            every { repeatPasswordValidatorUseCase(any(), any()) } returns true

            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(RegistrationEvent.OnRegisterClick)

                val state = awaitItem()
                assertThat(state.error).isEqualTo(R.string.error_empty_fields)
                coVerify(exactly = 0) { registerUserUseCase(any(), any(), any()) }

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given invalid email, when OnRegisterClick is sent, then error is invalid_email and register use case is not called`() =
        runTest {
            // given
            every { emptyFieldsValidatorUseCase(any()) } returns false
            every { emailValidatorUseCase(any()) } returns false
            every { passwordValidatorUseCase(any()) } returns false
            every { repeatPasswordValidatorUseCase(any(), any()) } returns true

            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(RegistrationEvent.OnRegisterClick)

                val state = awaitItem()
                assertThat(state.error).isEqualTo(R.string.error_invalid_email)
                coVerify(exactly = 0) { registerUserUseCase(any(), any(), any()) }

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given mismatching passwords, when OnRegisterClick is sent, then error is passwords_do_not_match and register use case is not called`() =
        runTest {
            // given
            every { emptyFieldsValidatorUseCase(any()) } returns false
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns true
            every { repeatPasswordValidatorUseCase(any(), any()) } returns false

            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(RegistrationEvent.OnRegisterClick)

                val state = awaitItem()
                assertThat(state.error).isEqualTo(R.string.error_passwords_do_not_match)
                coVerify(exactly = 0) { registerUserUseCase(any(), any(), any()) }

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given valid form, when OnRegisterClick is sent and api succeeds, then state emits success`() =
        runTest {
            // given
            every { emptyFieldsValidatorUseCase(any()) } returns false
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns true
            every { repeatPasswordValidatorUseCase(any(), any()) } returns true
            coEvery {
                registerUserUseCase(
                    any(),
                    any(),
                    any()
                )
            } returns ApiResult.Success(Unit)

            // when
            viewModel.onEvent(RegistrationEvent.OnRegisterClick)

            // then
            val state = viewModel.state.value
            assertThat(state.isLoading).isFalse()
            assertThat(state.error).isNull()
        }

    @Test
    fun `given valid form, when OnRegisterClick is sent and api succeeds, then register use case is called once with correct params`() =
        runTest {
            // given
            every { emptyFieldsValidatorUseCase(any()) } returns false
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns true
            every { repeatPasswordValidatorUseCase(any(), any()) } returns true
            coEvery {
                registerUserUseCase(
                    name = any(),
                    password = any(),
                    email = any()
                )
            } returns ApiResult.Success(Unit)

            // when
            viewModel.onEvent(RegistrationEvent.OnRegisterClick)

            // then
            coVerify(exactly = 1) {
                registerUserUseCase(
                    email = "giorgi@example.com",
                    name = "Giorgi",
                    password = "Password123"
                )
            }
        }

    @Test
    fun `given valid form, when OnRegisterClick is sent and api fails, then error is user_already_exists and loading is false`() =
        runTest {
            // given
            every { emptyFieldsValidatorUseCase(any()) } returns false
            every { emailValidatorUseCase(any()) } returns true
            every { passwordValidatorUseCase(any()) } returns true
            every { repeatPasswordValidatorUseCase(any(), any()) } returns true
            coEvery {
                registerUserUseCase(
                    name = any(),
                    password = any(),
                    email = any()
                )
            } returns ApiResult.Error(NetworkError.USER_ALREADY_EXISTS)

            // when & then
            viewModel.state.test {
                awaitItem()

                viewModel.onEvent(RegistrationEvent.OnRegisterClick)

                val finalState = awaitItem()
                assertThat(finalState.isLoading).isFalse()
                assertThat(finalState.error).isEqualTo(R.string.error_user_already_exists)

                cancelAndIgnoreRemainingEvents()
            }
        }
}

@Suppress("UNCHECKED_CAST")
private fun RegistrationVm.forceState(newState: RegistrationState) {
    val field = BaseVm::class.java.getDeclaredField("_state")
    field.isAccessible = true
    val flow = field.get(this) as MutableStateFlow<RegistrationState>
    flow.value = newState
}