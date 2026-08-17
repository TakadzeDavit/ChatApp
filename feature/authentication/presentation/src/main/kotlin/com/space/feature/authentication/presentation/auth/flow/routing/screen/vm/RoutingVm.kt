package com.space.feature.authentication.presentation.auth.flow.routing.screen.vm

import com.space.feature.authentication.presentation.auth.flow.routing.screen.contract.RoutingEvent
import com.space.feature.authentication.presentation.auth.flow.routing.screen.contract.RoutingState
import com.space.presentation.BaseVm

class RoutingVm : BaseVm<RoutingState, RoutingEvent>(RoutingState()) {
    override fun onEvent(event: RoutingEvent) {

    }
}