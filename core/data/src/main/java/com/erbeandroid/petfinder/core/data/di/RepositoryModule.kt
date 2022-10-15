package com.erbeandroid.petfinder.core.data.di

import com.erbeandroid.petfinder.core.data.repository.local.LocalRepository
import com.erbeandroid.petfinder.core.data.repository.local.TaskLocalRepository
import com.erbeandroid.petfinder.core.data.repository.remote.PetFinderRemoteRepository
import com.erbeandroid.petfinder.core.data.repository.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRemoteRepository(
        petFinderRemoteRepository: PetFinderRemoteRepository
    ): RemoteRepository

    @Binds
    fun bindLocalRepository(
        taskLocalRepository: TaskLocalRepository
    ): LocalRepository
}