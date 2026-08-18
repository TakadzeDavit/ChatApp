package com.space.feature.authentication.presentation.di

import com.space.domain.RegistrationScope
import com.space.domain.RoutingScope
import com.space.feature.authentication.presentation.auth.flow.registration.vm.RegistrationVm
import com.space.feature.authentication.presentation.auth.flow.routing.screen.vm.RoutingVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val authPresentationModule = module {
    scope<RoutingScope> {
        viewModel<RoutingVm>()
    }
    scope<RegistrationScope> {
        viewModel<RegistrationVm>()
    }
}