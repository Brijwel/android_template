package com.brijwel.androidtemplate.utils

object SecureKeys {
    const val AUTH = 1
    const val CHAT = 2
    const val BASE = 3

    init {
        System.loadLibrary("keys")
    }

    external fun getAPIKey(): String
    external fun getAPIKeyByType(string: Int): String
}
