package com.space.feature.authentication.presentation.auth.flow.routing.contract

import com.space.presentation.UiEvent

sealed interface RoutingEvent : UiEvent {
    data class OnThemeToggle(val isChecked: Boolean) : RoutingEvent
    data object OnNavigateRegistration : RoutingEvent
}