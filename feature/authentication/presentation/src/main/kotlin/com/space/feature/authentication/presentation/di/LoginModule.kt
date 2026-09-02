package com.space.feature.authentication.presentation.di

import com.space.domain.usecase.login.LoginUserUseCase
import com.space.feature.authentication.presentation.auth.flow.login.vm.LoginVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.viewModel

val loginModule = module {
    viewModel<LoginVm>()
    factory<LoginUserUseCase>()
}