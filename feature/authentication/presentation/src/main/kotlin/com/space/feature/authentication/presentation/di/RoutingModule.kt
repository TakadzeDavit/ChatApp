package com.space.feature.authentication.presentation.di

import com.space.domain.scope.AuthScope
import com.space.feature.authentication.presentation.auth.flow.routing.vm.RoutingVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val routingModule = module {
    scope<AuthScope.RoutingScope> {
        viewModel<RoutingVm>()
    }
}