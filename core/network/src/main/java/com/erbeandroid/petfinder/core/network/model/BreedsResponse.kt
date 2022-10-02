package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedsResponse(
    @field:Json(name = "breeds")
    val breeds: List<BreedResponse>?
)

@JsonClass(generateAdapter = true)
data class BreedResponse(
    @field:Json(name = "name")
    val name: String?
)