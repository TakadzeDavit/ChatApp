package com.space.feature.authentication.presentation.auth.flow.routing.vm

import com.space.feature.authentication.presentation.auth.flow.routing.contract.RoutingEvent
import com.space.feature.authentication.presentation.auth.flow.routing.contract.RoutingState
import com.space.feature.authentication.presentation.navigator.RegistrationScreenKey
import com.space.presentation.BaseVm

class RoutingVm(

) : BaseVm<RoutingState, RoutingEvent>(RoutingState()) {
    override fun onEvent(event: RoutingEvent) {
        when (event) {
            is RoutingEvent.OnThemeToggle -> updateState { copy(isDarkTheme = event.isChecked) }
            is RoutingEvent.OnNavigateRegistration -> flowNavigator { push(RegistrationScreenKey) }
        }
    }
}