package com.space.feature.authentication.presentation.di

import com.space.domain.usecase.register.RegisterUserUseCase
import com.space.feature.authentication.presentation.auth.flow.registration.vm.RegistrationVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.viewModel

val registrationModule = module {
    viewModel<RegistrationVm>()
    factory<RegisterUserUseCase>()
}