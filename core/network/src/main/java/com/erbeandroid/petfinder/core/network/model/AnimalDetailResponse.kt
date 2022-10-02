package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimalDetailResponse(
    @field:Json(name = "animal")
    val animal: AnimalResponse?
)