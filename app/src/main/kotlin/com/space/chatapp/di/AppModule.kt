package com.space.chatapp.di

import com.space.core.data.di.coreDataModule
import com.space.data.local.di.chatDataModule
import org.koin.dsl.module

val appModule = module {
    includes(
        coreDataModule,
        chatDataModule
    )
}