package com.erbeandroid.petfinder.core.data.repository

import androidx.paging.PagingData
import com.erbeandroid.petfinder.core.data.model.Animal
import com.erbeandroid.petfinder.core.data.model.AnimalDetail
import com.erbeandroid.petfinder.core.data.model.Breed
import com.erbeandroid.petfinder.core.data.model.Type
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    suspend fun getTypes(): Flow<List<Type>>

    suspend fun getBreeds(type: String): Flow<List<Breed>>

    fun getAnimals(
        type: String,
        breed: String
    ): Flow<PagingData<Animal>>

    suspend fun getAnimalDetail(id: Int): Flow<AnimalDetail?>
}