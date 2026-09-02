package com.space.feature.authentication.presentation.auth.flow.login.contract

import androidx.compose.foundation.text.input.TextFieldState
import com.space.presentation.DataState
import com.space.presentation.UiState

data class LoginState(
    val actionState: DataState<Unit> = DataState.Idle,
    val isDarkTheme: Boolean = false,
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
) : UiState