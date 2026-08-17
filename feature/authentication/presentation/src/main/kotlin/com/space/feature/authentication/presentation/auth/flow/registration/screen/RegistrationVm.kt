package com.space.feature.authentication.presentation.auth.flow.registration.screen

import com.space.presentation.BaseVm
import com.space.presentation.UiEvent
import com.space.presentation.UiState

class RegistrationVm : BaseVm<RegistrationState, RegistrationEvent>(RegistrationState()) {
    override fun onEvent(event: RegistrationEvent) {
    }
}

data class RegistrationState (
    val isLoading: Boolean = false
) : UiState

sealed interface RegistrationEvent : UiEvent {

}