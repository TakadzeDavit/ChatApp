package com.space.feature.authentication.presentation.auth.flow.registration.vm

import androidx.lifecycle.viewModelScope
import com.space.authentication.presentation.R
import com.space.core.domain.common.ApiResult
import com.space.core.domain.validator.EmailValidatorUseCase
import com.space.core.domain.validator.EmptyFieldsValidatorUseCase
import com.space.core.domain.validator.PasswordValidatorUseCase
import com.space.core.domain.validator.RepeatPasswordValidatorUseCase
import com.space.domain.usecase.RegisterUserUseCase
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationEvent
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationState
import com.space.presentation.BaseVm
import kotlinx.coroutines.launch

class RegistrationVm(
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val emptyFieldsValidatorUseCase: EmptyFieldsValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase,
    private val repeatPasswordValidatorUseCase: RepeatPasswordValidatorUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : BaseVm<RegistrationState, RegistrationEvent>(RegistrationState()) {

    override fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnThemeToggle -> updateState { copy(isDarkTheme = event.isChecked) }
            is RegistrationEvent.OnRegisterClick -> registerUser()
        }
    }

    private fun registerUser() {
        val currentState = state.value

        // string values
        val name = currentState.name.text.toString()
        val email = currentState.email.text.toString()
        val password = currentState.password.text.toString()
        val repeatPassword = currentState.repeatPassword.text.toString()

        // validations
        val isFieldsEmpty =
            emptyFieldsValidatorUseCase(listOf(name, email, password, repeatPassword))
        val isEmailValid = emailValidatorUseCase(email)
        val isPasswordValid = passwordValidatorUseCase(password)
        val isRepeatPasswordValid = repeatPasswordValidatorUseCase(password, repeatPassword)

        // check validations and get resource id
        val errorMessageResId = when {
            isFieldsEmpty -> R.string.error_empty_fields
            !isEmailValid -> R.string.error_invalid_email
            isPasswordValid -> R.string.error_invalid_password
            !isRepeatPasswordValid -> R.string.error_passwords_do_not_match
            else -> null
        }

        if (errorMessageResId != null) {
            updateState { copy(error = errorMessageResId) }
            return
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true, error = null) }

            val result = registerUserUseCase(
                email = email,
                name = name,
                password = password
            )

            when (result) {
                is ApiResult.Error -> {
                    updateState {
                        copy(
                            isLoading = false,
                            error = R.string.error_user_already_exists
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