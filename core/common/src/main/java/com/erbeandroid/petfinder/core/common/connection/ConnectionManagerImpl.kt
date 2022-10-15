package com.erbeandroid.petfinder.core.common.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : ConnectionManager {

    private val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    override fun isConnected(): Boolean {
        return connectivityManager.activeNetwork != null
    }

    override fun connectionStatus(): Flow<Boolean> = callbackFlow {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }.flowOn(ioDispatcher)
}