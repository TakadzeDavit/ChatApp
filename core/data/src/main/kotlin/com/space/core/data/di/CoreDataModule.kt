package com.space.core.data.di

import androidx.room.Room
import com.space.core.data.local.database.ChatAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDataModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ChatAppDatabase::class.java,
            name = "chat_app_database"
        ).fallbackToDestructiveMigration(false).build()
    }

    single { get<ChatAppDatabase>().userDao() }
}