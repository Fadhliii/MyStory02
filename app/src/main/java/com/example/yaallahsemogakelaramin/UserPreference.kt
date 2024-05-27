package com.example.yaallahsemogakelaramin

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun getUser(): Flow<String?> {
        return dataStore.data.map {
            it[STATE_KEY]
        }
    }

    fun getToken() {
        TODO("Not yet implemented")
    }


    companion object {
        private var INSTANCE: UserPreference? = null
        private val TOKEN_KEY = stringPreferencesKey("token") // untuk menyimpan token user
        private val STATE_KEY = stringPreferencesKey("state") // untuk menyimpan state user.
        // state user adalah apakah user sudah login atau belum.


    }
}
