package com.space.feature.authentication.presentation.auth.flow.login.vm

import androidx.lifecycle.viewModelScope
import com.space.authentication.presentation.R
import com.space.core.domain.common.ApiResult
import com.space.domain.usecase.login.LoginUserUseCase
import com.space.domain.usecase.validator.EmailValidatorUseCase
import com.space.domain.usecase.validator.PasswordValidatorUseCase
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginEvent
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginState
import com.space.presentation.BaseVm
import kotlinx.coroutines.launch

class LoginVm(
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : BaseVm<LoginState, LoginEvent>(LoginState()) {
    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnThemeToggle -> updateState { copy(isDarkTheme = event.isChecked) }
            is LoginEvent.OnLoginClick -> login()
        }
    }

    private fun login() {
        val loginState = state.value

        // string values
        val email = loginState.email.text.toString()
        val password = loginState.password.text.toString()

        // validations
        val isEmailValid = emailValidatorUseCase(email = email)
        val isPasswordValid = passwordValidatorUseCase(password = password)

        // error resource id
        val errorMessageResId = when {
            !isEmailValid -> R.string.error_invalid_email
            isPasswordValid -> R.string.error_invalid_password
            else -> null
        }

        if (errorMessageResId != null) {
            updateState { copy(error = errorMessageResId) }
            return
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true, error = null) }

            val loginResult = loginUserUseCase.invoke(email = email, password = password)

            when (loginResult) {
                is ApiResult.Error -> {
                    updateState {
                        copy(
                            isLoading = false,
                            error = R.string.invalid_password_or_email
                        )
                    }
                }
                is ApiResult.Success -> {
                    updateState {
                        copy(
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }
}