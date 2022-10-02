package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant

@JsonClass(generateAdapter = true)
data class TokenResponse(
    @field:Json(name = "token_type")
    val tokenType: String?,
    @field:Json(name = "expires_in")
    val expiresIn: Int?,
    @field:Json(name = "access_token")
    val accessToken: String?
) {

    companion object {
        val INVALID = TokenResponse("", -1, "")
    }

    fun isValid(): Boolean {
        return tokenType != null && tokenType.isNotEmpty() &&
                expiresIn != null && expiresIn >= 0 &&
                accessToken != null && accessToken.isNotEmpty()
    }

    fun getExpiresIn(): Long {
        if (expiresIn == null) return 0L
        return Instant.now().plusSeconds(expiresIn.toLong()).epochSecond
    }
}