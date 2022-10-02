package com.erbeandroid.petfinder.core.network.interceptor

import com.erbeandroid.petfinder.core.network.model.TokenResponse
import com.erbeandroid.petfinder.core.network.util.NetworkConstant.AUTH_ENDPOINT
import com.erbeandroid.petfinder.core.network.util.NetworkConstant.CLIENT_ID
import com.erbeandroid.petfinder.core.network.util.NetworkConstant.CLIENT_SECRET
import com.erbeandroid.petfinder.core.preference.datasource.PreferenceDataSource
import com.squareup.moshi.Moshi
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.time.Instant
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource,
    private val moshi: Moshi
) : Interceptor {

    companion object {
        const val UNAUTHORIZED = 401
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferenceDataSource.getAccessToken()
        val expiresIn = Instant.ofEpochSecond(preferenceDataSource.getExpiresIn())

        val request = chain.request()

        val interceptedRequest: Request =
            if (expiresIn.isAfter(Instant.now())) {
                // API Request
                chain.createAuthenticatedRequest(accessToken)
            } else {
                // API Authentication
                val tokenResponse = chain.refreshToken()

                // API Request
                chain.tokenRequest(tokenResponse, request)
            }

        return chain.proceedDeletingTokenIfUnauthorized(interceptedRequest)
    }

    private fun Interceptor.Chain.createAuthenticatedRequest(accessToken: String): Request {
        return request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
    }

    private fun Interceptor.Chain.refreshToken(): Response {
        val body = FormBody.Builder()
            .add("grant_type", "client_credentials")
            .add("client_id", CLIENT_ID)
            .add("client_secret", CLIENT_SECRET)
            .build()

        val url = request()
            .url
            .newBuilder(AUTH_ENDPOINT)!!
            .build()

        val tokenRefresh = request()
            .newBuilder()
            .post(body)
            .url(url)
            .build()

        return proceedDeletingTokenIfUnauthorized(tokenRefresh)
    }

    private fun Interceptor.Chain.tokenRequest(tokenResponse: Response, request: Request): Request {
        return if (tokenResponse.isSuccessful) {
            val newToken = mapToken(tokenResponse)
            if (newToken.isValid()) {
                storeToken(newToken)
                createAuthenticatedRequest(newToken.accessToken!!)
            } else {
                request
            }
        } else {
            request
        }
    }

    private fun Interceptor.Chain.proceedDeletingTokenIfUnauthorized(request: Request): Response {
        val response = proceed(request)

        if (response.code == UNAUTHORIZED) {
            preferenceDataSource.deleteToken()
        }

        return response
    }

    private fun mapToken(tokenResponse: Response): TokenResponse {
        val tokenAdapter = moshi.adapter(TokenResponse::class.java)
        val responseBody = tokenResponse.body!!

        return tokenAdapter.fromJson(responseBody.string()) ?: TokenResponse.INVALID
    }

    private fun storeToken(tokenResponse: TokenResponse) {
        with(preferenceDataSource) {
            putTokenType(tokenResponse.tokenType!!)
            putExpiresIn(tokenResponse.getExpiresIn())
            putAccessToken(tokenResponse.accessToken!!)
        }
    }
}