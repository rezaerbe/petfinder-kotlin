package com.erbeandroid.petfinder.core.preference.datasource

import android.content.SharedPreferences
import com.erbeandroid.petfinder.core.preference.util.PreferenceConstant.KEY_ACCESS_TOKEN
import com.erbeandroid.petfinder.core.preference.util.PreferenceConstant.KEY_EXPIRES_IN
import com.erbeandroid.petfinder.core.preference.util.PreferenceConstant.KEY_TOKEN_TYPE
import javax.inject.Inject

class PetFinderPreferenceDataSource @Inject constructor(
    private val preferences: SharedPreferences
) : PreferenceDataSource {

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    override fun putTokenType(tokenType: String) {
        edit { putString(KEY_TOKEN_TYPE, tokenType) }
    }

    override fun putExpiresIn(expiresIn: Long) {
        edit { putLong(KEY_EXPIRES_IN, expiresIn) }
    }

    override fun putAccessToken(accessToken: String) {
        edit { putString(KEY_ACCESS_TOKEN, accessToken) }
    }

    override fun getExpiresIn(): Long {
        return preferences.getLong(KEY_EXPIRES_IN, -1)
    }

    override fun getAccessToken(): String {
        return preferences.getString(KEY_ACCESS_TOKEN, "").orEmpty()
    }

    override fun deleteToken() {
        edit {
            remove(KEY_TOKEN_TYPE)
            remove(KEY_EXPIRES_IN)
            remove(KEY_ACCESS_TOKEN)
        }
    }
}