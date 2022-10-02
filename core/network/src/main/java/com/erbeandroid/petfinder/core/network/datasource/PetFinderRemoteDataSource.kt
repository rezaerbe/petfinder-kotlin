package com.erbeandroid.petfinder.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.IO
import com.erbeandroid.petfinder.core.network.model.AnimalDetailResponse
import com.erbeandroid.petfinder.core.network.model.AnimalResponse
import com.erbeandroid.petfinder.core.network.model.BreedsResponse
import com.erbeandroid.petfinder.core.network.model.TypesResponse
import com.erbeandroid.petfinder.core.network.service.PetFinderService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PetFinderRemoteDataSource @Inject constructor(
    private val petFinderService: PetFinderService,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {

    override suspend fun getTypes(): Flow<TypesResponse> =
        flow {
            val types = petFinderService.getTypes()
            emit(types)
        }.flowOn(ioDispatcher)

    override suspend fun getBreeds(type: String): Flow<BreedsResponse> =
        flow {
            val breeds = petFinderService.getBreeds(type)
            emit(breeds)
        }.flowOn(ioDispatcher)

    override fun getAnimals(type: String, breed: String): Flow<PagingData<AnimalResponse>> =
        Pager(PagingConfig(pageSize = 20)) {
            AnimalPagingDataSource(petFinderService, type, breed)
        }.flow.flowOn(ioDispatcher)

    override suspend fun getAnimalDetail(id: Int): Flow<AnimalDetailResponse> =
        flow {
            val animalDetail = petFinderService.getAnimalDetail(id)
            emit(animalDetail)
        }.flowOn(ioDispatcher)
}