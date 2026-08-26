package com.space.feature.authentication.presentation.di

import com.space.domain.scope.AuthScope
import com.space.feature.authentication.presentation.auth.flow.login.vm.LoginVm
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val loginModule = module {
    scope<AuthScope.LoginScope> {
        viewModel<LoginVm>()
    }
}