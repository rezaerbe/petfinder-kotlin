package com.erbeandroid.petfinder.core.data.repository.remote

import androidx.paging.PagingData
import com.erbeandroid.petfinder.core.data.model.remote.Animal
import com.erbeandroid.petfinder.core.data.model.remote.AnimalDetail
import com.erbeandroid.petfinder.core.data.model.remote.Breed
import com.erbeandroid.petfinder.core.data.model.remote.Type
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    suspend fun getTypes(): Flow<List<Type>>
    suspend fun getBreeds(type: String): Flow<List<Breed>>
    fun getAnimals(type: String, breed: String): Flow<PagingData<Animal>>
    suspend fun getAnimalDetail(id: Int): Flow<AnimalDetail?>
}