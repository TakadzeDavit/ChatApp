package com.space.chatapp.di

import com.space.data.local.di.chatDataModule
import org.koin.dsl.module

val appModule = module {
    includes(
        chatDataModule
    )
}