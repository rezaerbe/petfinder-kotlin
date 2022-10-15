package com.erbeandroid.petfinder.core.network.datasource

import androidx.paging.PagingData
import com.erbeandroid.petfinder.core.network.model.AnimalDetailResponse
import com.erbeandroid.petfinder.core.network.model.AnimalResponse
import com.erbeandroid.petfinder.core.network.model.BreedsResponse
import com.erbeandroid.petfinder.core.network.model.TypesResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getTypes(): Flow<TypesResponse>
    suspend fun getBreeds(type: String): Flow<BreedsResponse>
    fun getAnimals(type: String, breed: String): Flow<PagingData<AnimalResponse>>
    suspend fun getAnimalDetail(id: Int): Flow<AnimalDetailResponse>
}