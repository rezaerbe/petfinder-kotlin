package com.erbeandroid.petfinder.core.network.service

import com.erbeandroid.petfinder.core.network.model.AnimalDetailResponse
import com.erbeandroid.petfinder.core.network.model.BreedsResponse
import com.erbeandroid.petfinder.core.network.model.PaginatedAnimalResponse
import com.erbeandroid.petfinder.core.network.model.TypesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PetFinderService {

    @GET("types")
    suspend fun getTypes(): TypesResponse

    @GET("types/{type}/breeds")
    suspend fun getBreeds(
        @Path("type") type: String
    ): BreedsResponse

    @GET("animals")
    suspend fun getAnimals(
        @Query("type") type: String,
        @Query("breed") breed: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PaginatedAnimalResponse

    @GET("animals/{id}")
    suspend fun getAnimalDetail(
        @Path("id") id: Int
    ): AnimalDetailResponse
}