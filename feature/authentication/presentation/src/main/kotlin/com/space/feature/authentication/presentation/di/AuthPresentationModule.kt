package com.space.feature.authentication.presentation.di

import com.space.domain.usecase.validator.EmailValidatorUseCase
import com.space.domain.usecase.validator.EmptyFieldsValidatorUseCase
import com.space.domain.usecase.validator.PasswordValidatorUseCase
import com.space.domain.usecase.validator.RepeatPasswordValidatorUseCase
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory

val authPresentationModule = module {
    includes(registrationModule)
    includes(routingModule)
    includes(loginModule)

    factory<EmailValidatorUseCase>()
    factory<EmptyFieldsValidatorUseCase>()
    factory<PasswordValidatorUseCase>()
    factory<RepeatPasswordValidatorUseCase>()
}