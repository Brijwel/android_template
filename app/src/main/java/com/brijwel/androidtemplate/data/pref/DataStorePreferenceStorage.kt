package com.brijwel.androidtemplate.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.brijwel.androidtemplate.data.pref.DataStorePreferenceStorage.PreferencesKeys.PREF_ON_BOARDING_COMPLETED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {

    companion object {
        const val PREFS_NAME = "preference_storage"
    }

    object PreferencesKeys {
        val PREF_ON_BOARDING_COMPLETED = booleanPreferencesKey("pref_on_boarding_completed")
    }

    override val onBoardingCompleted: Flow<Boolean> =
        dataStore.data.map { it[PREF_ON_BOARDING_COMPLETED] ?: false }

    override suspend fun completeOnBoarding(complete: Boolean) {
        dataStore.edit {
            it[PREF_ON_BOARDING_COMPLETED] = complete
        }
    }
}
