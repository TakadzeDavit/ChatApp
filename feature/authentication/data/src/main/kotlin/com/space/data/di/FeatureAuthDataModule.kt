package com.space.data.di

import com.space.core.data.di.coreDataModule
import com.space.data.repository.RegisterRepositoryImpl
import com.space.domain.repository.RegisterRepository
import com.space.domain.scope.AuthScope
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped

val featureAuthDataModule = module {
    includes(coreDataModule)

    scope<AuthScope.RegistrationScope> {
        scoped<RegisterRepositoryImpl>() bind RegisterRepository::class
    }
}