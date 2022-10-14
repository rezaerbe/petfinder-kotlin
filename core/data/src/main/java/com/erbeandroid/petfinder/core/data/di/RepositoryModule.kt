package com.erbeandroid.petfinder.core.data.di

import com.erbeandroid.petfinder.core.data.repository.LocalRepository
import com.erbeandroid.petfinder.core.data.repository.PetFinderRemoteRepository
import com.erbeandroid.petfinder.core.data.repository.RemoteRepository
import com.erbeandroid.petfinder.core.data.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRemoteRepository(
        petFinderRepository: PetFinderRemoteRepository
    ): RemoteRepository

    @Binds
    fun bindLocalRepository(
        taskRepository: TaskRepository
    ): LocalRepository
}