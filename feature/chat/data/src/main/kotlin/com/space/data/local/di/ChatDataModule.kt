package com.space.data.local.di

import com.space.core.data.di.coreDataModule
import com.space.data.local.mapper.ChatResponseMapperToEntity
import com.space.data.local.mapper.MessageEntityMapperToResponse
import com.space.data.local.mapper.MessageResponseMapperToEntity
import com.space.data.local.repository.ChatRepositoryImpl
import com.space.domain.repository.ChatRepository
import com.space.domain.scope.ChatScope
import com.space.domain.usecase.DeleteChatUseCase
import com.space.domain.usecase.GetAllChatsUseCase
import org.koin.dsl.*
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped
import org.koin.plugin.module.dsl.single
import kotlin.math.sin

val chatDataModule = module {
    includes(coreDataModule)

    single { ChatResponseMapperToEntity() }
    single { MessageEntityMapperToResponse() }
    single { MessageResponseMapperToEntity() }
    single<ChatRepositoryImpl>() bind ChatRepository::class
}