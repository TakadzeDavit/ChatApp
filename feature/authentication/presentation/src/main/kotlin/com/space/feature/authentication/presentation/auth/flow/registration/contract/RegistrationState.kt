package com.space.feature.authentication.presentation.auth.flow.registration.contract

import androidx.compose.foundation.text.input.TextFieldState
import com.space.presentation.UiState

data class RegistrationState (
    val isDarkTheme: Boolean = false,
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val repeatPassword: TextFieldState = TextFieldState(),
) : UiState