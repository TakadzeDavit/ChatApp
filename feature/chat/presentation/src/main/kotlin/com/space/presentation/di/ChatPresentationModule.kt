package com.space.presentation.di

import com.space.domain.scope.ChatScope
import com.space.domain.usecase.DeleteChatUseCase
import com.space.domain.usecase.GetAllChatsUseCase
import com.space.presentation.chat.flow.chat_list.vm.ChatListVm
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chatPresentationModule = module {
    scope<ChatScope.ChatListScope> {
        scoped { GetAllChatsUseCase(get()) }
        scoped { DeleteChatUseCase(get()) }
        viewModel { params ->
            ChatListVm(
                deleteChatUseCase = get(),
                getAllChatsUseCase = get(),
                userId = params.get()
            )
        }
    }
}