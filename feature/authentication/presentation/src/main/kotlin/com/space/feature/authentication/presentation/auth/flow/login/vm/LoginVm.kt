package com.space.feature.authentication.presentation.auth.flow.login.vm

import androidx.lifecycle.viewModelScope
import com.space.authentication.presentation.R
import com.space.core.domain.common.ApiResult
import com.space.core.domain.common.NetworkError
import com.space.domain.usecase.login.LoginUserUseCase
import com.space.domain.usecase.validator.EmailValidatorUseCase
import com.space.domain.usecase.validator.EmptyFieldsValidatorUseCase
import com.space.domain.usecase.validator.PasswordValidatorUseCase
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginEvent
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginState
import com.space.presentation.BaseVm
import com.space.presentation.DataState
import kotlinx.coroutines.launch

class LoginVm(
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val emptyFieldsValidatorUseCase: EmptyFieldsValidatorUseCase,
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

        val fields = listOf(email, password)

        // validations
        val isEmailValid = emailValidatorUseCase(email = email)
        val isFieldsEmpty = emptyFieldsValidatorUseCase(fields = fields)

        val errorMessageResId = when {
            isFieldsEmpty -> R.string.error_empty_fields
            !isEmailValid -> R.string.error_invalid_email
            else -> null
        }

        if (errorMessageResId != null) {
            updateState {
                copy(
                    actionState = DataState.Error(
                        errorType = NetworkError.SOMETHING_WENT_WRONG,
                        resourceId = errorMessageResId
                    )
                )
            }
            return
        }

        viewModelScope.launch {
            updateState { copy(actionState = DataState.Loading) }

            val loginResult = loginUserUseCase(email = email, password = password)

            when (loginResult) {
                is ApiResult.Error -> {
                    updateState {
                        copy(
                            actionState = DataState.Error(
                                errorType = loginResult.errorType,
                                message = loginResult.message,
                                resourceId = R.string.invalid_password_or_email
                            )
                        )
                    }
                }

                is ApiResult.Success -> {
                    updateState {
                        copy(actionState = DataState.Success(Unit))
                    }
                }
            }
        }
    }
}