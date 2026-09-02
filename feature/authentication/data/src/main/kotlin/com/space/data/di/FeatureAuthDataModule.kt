package com.space.data.di

import com.space.core.data.di.coreDataModule
import org.koin.dsl.module

val authDataModule = module {
    includes(coreDataModule)
    includes(registerModule)
    includes(loginModule)
}