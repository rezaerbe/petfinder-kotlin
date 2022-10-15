package com.erbeandroid.petfinder.core.common.dispatcher

import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.Default
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Dispatcher(IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}