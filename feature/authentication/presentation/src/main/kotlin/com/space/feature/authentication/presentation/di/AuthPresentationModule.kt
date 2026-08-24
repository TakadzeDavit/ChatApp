package com.space.feature.authentication.presentation.di

import com.space.core.domain.validator.EmailValidatorUseCase
import com.space.core.domain.validator.EmptyFieldsValidatorUseCase
import com.space.core.domain.validator.PasswordValidatorUseCase
import com.space.core.domain.validator.RepeatPasswordValidatorUseCase
import com.space.domain.scope.AuthScope
import com.space.domain.usecase.RegisterUserUseCase
import com.space.feature.authentication.presentation.auth.flow.registration.vm.RegistrationVm
import com.space.feature.authentication.presentation.auth.flow.routing.vm.RoutingVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped
import org.koin.plugin.module.dsl.viewModel

val authPresentationModule = module {
    scope<AuthScope.Parent> {
        scoped<EmailValidatorUseCase>()
        scoped<EmptyFieldsValidatorUseCase>()
        scoped<PasswordValidatorUseCase>()
        scoped<RepeatPasswordValidatorUseCase>()
    }

    scope<AuthScope.RoutingScope> {
        viewModel<RoutingVm>()
    }

    scope<AuthScope.RegistrationScope> {
        viewModel<RegistrationVm>()
        scoped<RegisterUserUseCase>()
    }
}