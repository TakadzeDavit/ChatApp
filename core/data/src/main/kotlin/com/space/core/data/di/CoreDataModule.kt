package com.space.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.space.core.data.local.database.ChatAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

val coreDataModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ChatAppDatabase::class.java,
            name = "chat_app_database"
        ).fallbackToDestructiveMigration(false).build()
    }

    single { get<ChatAppDatabase>().userDao() }

    single<DataStore<Preferences>> {
        androidContext().dataStore
    }
}