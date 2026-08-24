package com.space.data.local.di

import com.space.data.local.repository.ChatRepositoryImpl
import com.space.domain.repository.ChatRepository
import com.space.domain.scope.ChatScope
import org.koin.dsl.*
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped

val featureChatDataModule = module {
    scope<ChatScope.ParentScope> {
        scoped<ChatRepositoryImpl>() bind ChatRepository::class
    }
}