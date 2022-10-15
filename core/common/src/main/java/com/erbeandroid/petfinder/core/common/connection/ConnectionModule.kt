package com.erbeandroid.petfinder.core.common.connection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ConnectionModule {

    @Binds
    fun bindConnectionManger(
        connectionManagerImpl: ConnectionManagerImpl
    ): ConnectionManager
}