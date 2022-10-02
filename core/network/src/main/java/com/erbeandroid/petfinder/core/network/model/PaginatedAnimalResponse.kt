package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginatedAnimalResponse(
    @field:Json(name = "animals")
    val animals: List<AnimalResponse>?,
    @field:Json(name = "pagination")
    val pagination: PaginationResponse?,
)