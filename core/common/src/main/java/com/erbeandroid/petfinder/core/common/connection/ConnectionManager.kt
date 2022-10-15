package com.erbeandroid.petfinder.core.common.connection

import kotlinx.coroutines.flow.Flow

interface ConnectionManager {

    fun isConnected(): Boolean
    fun connectionStatus(): Flow<Boolean>
}