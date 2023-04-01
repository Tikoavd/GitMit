package com.practicework.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DATA_STORE_PREF_NAME = "GitMitDataStore"
const val IMAGE_KEY = "ImageKey"
const val THEME_KEY = "ThemeKey"
const val IMAGE_FILE_NAME = "profile.png"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_PREF_NAME)

suspend fun Context.saveImage(isSave: Boolean) {
    dataStore.edit { pref ->
        pref[booleanPreferencesKey(IMAGE_KEY)] = isSave
    }
}

fun Context.getImage(): Flow<Boolean> {
    return dataStore.data.map { pref ->
        pref[booleanPreferencesKey(IMAGE_KEY)] ?: false
    }
}

suspend fun Context.saveNightMode(isNightMode : Boolean) {
    dataStore.edit { pref ->
        pref[booleanPreferencesKey(THEME_KEY)] = isNightMode
    }
}

fun Context.getNightMode() : Flow<Boolean?> {
    return dataStore.data.map { pref ->
        pref[booleanPreferencesKey(THEME_KEY)]
    }
}

suspend fun Context.clearPrefs() {
    dataStore.edit { pref ->
        pref.clear()
    }
}
