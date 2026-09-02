package com.space.presentation.di

import com.space.domain.usecase.DeleteChatUseCase
import com.space.domain.usecase.GetAllChatsUseCase
import com.space.presentation.chat.flow.chat_list.vm.ChatListVm
import org.koin.dsl.module

val chatPresentationModule = module {
    single { GetAllChatsUseCase(get()) }
    single { DeleteChatUseCase(get()) }
    single { params -> ChatListVm(get(), get(), userId = params.get()) }
}