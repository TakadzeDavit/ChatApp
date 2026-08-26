package com.space.feature.authentication.presentation.auth.flow.login.contract

import com.space.presentation.UiEvent

sealed interface LoginEvent : UiEvent {
    data class OnThemeToggle(val isChecked: Boolean) : LoginEvent
    data object OnLoginClick : LoginEvent
}