package com.space.data.di

import com.space.data.mapper.UserEntityMapperToDomain
import com.space.data.repository.login.LoginRepositoryImpl
import com.space.domain.repository.LoginRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory

val loginModule = module {
    factory<LoginRepositoryImpl>() bind LoginRepository::class
    factory<UserEntityMapperToDomain>()
}