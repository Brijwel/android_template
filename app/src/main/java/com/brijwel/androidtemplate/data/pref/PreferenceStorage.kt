package com.brijwel.androidtemplate.data.pref

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    val onBoardingCompleted: Flow<Boolean>
    suspend fun completeOnBoarding(complete: Boolean)
}
