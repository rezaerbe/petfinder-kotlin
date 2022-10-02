package com.erbeandroid.petfinder.core.preference.datasource

interface PreferenceDataSource {

    fun putTokenType(tokenType: String)
    fun putExpiresIn(expiresIn: Long)
    fun putAccessToken(accessToken: String)

    fun getExpiresIn(): Long
    fun getAccessToken(): String

    fun deleteToken()
}