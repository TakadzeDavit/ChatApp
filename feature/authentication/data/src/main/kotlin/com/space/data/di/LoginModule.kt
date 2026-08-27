package com.space.data.di

import com.space.data.mapper.UserEntityMapperToDomain
import com.space.data.repository.login.LoginRepositoryImpl
import com.space.domain.repository.LoginRepository
import com.space.domain.scope.AuthScope
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped

val loginModule = module {
    scope<AuthScope.LoginScope> {
        scoped<LoginRepositoryImpl>() bind LoginRepository::class
        scoped<UserEntityMapperToDomain>()
    }
}