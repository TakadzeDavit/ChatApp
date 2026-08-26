package com.space.chatapp.di

import com.space.data.local.di.chatDataModule
import com.space.presentation.di.chatPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(
        chatDataModule,
        chatPresentationModule
    )
}