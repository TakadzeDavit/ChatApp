package com.space.data.local.di

import com.space.core.data.di.coreDataModule
import com.space.data.local.mapper.ChatResponseMapperToEntity
import com.space.data.local.mapper.MessageEntityMapperToResponse
import com.space.data.local.mapper.MessageResponseMapperToEntity
import com.space.data.local.repository.ChatRepositoryImpl
import com.space.domain.repository.ChatRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val chatDataModule = module {
    includes(coreDataModule)

    single { ChatResponseMapperToEntity() }
    single { MessageEntityMapperToResponse() }
    single { MessageResponseMapperToEntity() }
    single<ChatRepositoryImpl>() bind ChatRepository::class
}