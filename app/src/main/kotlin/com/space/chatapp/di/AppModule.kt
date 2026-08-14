package com.space.chatapp.di

import com.space.core.data.di.coreDataModule
import org.koin.dsl.module

val appModule = module {
    includes(coreDataModule)
}