package com.erbeandroid.petfinder.core.data.di

import com.erbeandroid.petfinder.core.data.repository.PetFinderRepository
import com.erbeandroid.petfinder.core.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsRepository(
        petFinderRepository: PetFinderRepository
    ): Repository
}