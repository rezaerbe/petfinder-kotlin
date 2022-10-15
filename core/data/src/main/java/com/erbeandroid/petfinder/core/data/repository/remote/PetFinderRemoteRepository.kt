package com.erbeandroid.petfinder.core.data.repository.remote

import androidx.paging.PagingData
import androidx.paging.map
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.Default
import com.erbeandroid.petfinder.core.data.model.remote.Animal
import com.erbeandroid.petfinder.core.data.model.remote.AnimalDetail
import com.erbeandroid.petfinder.core.data.model.remote.Breed
import com.erbeandroid.petfinder.core.data.model.remote.Type
import com.erbeandroid.petfinder.core.data.model.remote.asDomain
import com.erbeandroid.petfinder.core.data.model.remote.asDomainDetail
import com.erbeandroid.petfinder.core.network.datasource.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PetFinderRemoteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher
) : RemoteRepository {

    override suspend fun getTypes(): Flow<List<Type>> =
        remoteDataSource.getTypes()
            .map { typesResponse ->
                typesResponse.types?.map { typeResponse ->
                    typeResponse.asDomain()
                } ?: emptyList()
            }.flowOn(defaultDispatcher)

    override suspend fun getBreeds(type: String): Flow<List<Breed>> =
        remoteDataSource.getBreeds(type)
            .map { breedsResponse ->
                breedsResponse.breeds?.map { breedResponse ->
                    breedResponse.asDomain()
                } ?: emptyList()
            }.flowOn(defaultDispatcher)

    override fun getAnimals(type: String, breed: String): Flow<PagingData<Animal>> =
        remoteDataSource.getAnimals(type, breed)
            .map { pagingData ->
                pagingData.map { animalResponse ->
                    animalResponse.asDomain()
                }
            }.flowOn(defaultDispatcher)

    override suspend fun getAnimalDetail(id: Int): Flow<AnimalDetail?> =
        remoteDataSource.getAnimalDetail(id)
            .map { animalDetailResponse ->
                animalDetailResponse.animal?.asDomainDetail()
            }.flowOn(defaultDispatcher)
}