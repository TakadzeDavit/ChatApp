package com.space.data.di

import com.space.data.repository.register.RegisterRepositoryImpl
import com.space.domain.repository.RegisterRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.scoped

val registerModule = module {
    factory<RegisterRepositoryImpl>() bind RegisterRepository::class
}