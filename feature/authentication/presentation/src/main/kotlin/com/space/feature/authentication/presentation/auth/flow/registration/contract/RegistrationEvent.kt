package com.space.feature.authentication.presentation.auth.flow.registration.contract

import com.space.presentation.UiEvent

sealed interface RegistrationEvent : UiEvent {
    data class OnThemeToggle(val isChecked: Boolean) : RegistrationEvent
    data object OnRegisterClick : RegistrationEvent
}