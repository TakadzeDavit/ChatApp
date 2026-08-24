package com.space.chatapp.di

import com.space.core.data.di.coreDataModule
import com.space.data.di.featureAuthDataModule
import com.space.feature.authentication.presentation.di.authPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(
        authPresentationModule,
        featureAuthDataModule
    )
}