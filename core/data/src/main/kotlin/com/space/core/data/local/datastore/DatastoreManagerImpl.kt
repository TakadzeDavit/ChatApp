package com.space.core.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManagerImpl(private val dataStore: DataStore<Preferences>) : DatastoreManager {
    override fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map { preferences -> preferences[key] ?: defaultValue }
    }

    override suspend fun <T> setPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

    override suspend fun removePreferences(keys: List<Preferences.Key<*>>) {
        dataStore.edit { preferences ->
            keys.forEach { key ->
                preferences.remove(key)
            }
        }
    }
}