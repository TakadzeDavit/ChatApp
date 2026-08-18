package com.space.feature.authentication.presentation.auth.flow.registration.vm

import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationEvent
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationState
import com.space.feature.authentication.presentation.auth.flow.routing.screen.contract.RoutingEvent
import com.space.feature.authentication.presentation.navigator.RegistrationScreenKey
import com.space.presentation.BaseVm

class RegistrationVm : BaseVm<RegistrationState, RegistrationEvent>(RegistrationState()) {


    override fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnThemeToggle -> updateState { copy(isDarkTheme = event.isChecked) }
        }
    }

}