package com.space.feature.authentication.presentation.di

import com.space.feature.authentication.presentation.auth.flow.routing.vm.RoutingVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val routingModule = module {
    viewModel<RoutingVm>()
}