package com.space.chatapp.di

import com.space.data.local.di.chatDataModule
import com.space.presentation.di.chatPresentationModule
import com.space.data.di.authDataModule
import com.space.feature.authentication.presentation.di.authPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(
        authPresentationModule,
        authDataModule,
        chatDataModule
        chatDataModule,
        chatPresentationModule
    )
}