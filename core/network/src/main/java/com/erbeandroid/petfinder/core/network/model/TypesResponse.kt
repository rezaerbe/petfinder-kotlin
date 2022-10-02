package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypesResponse(
    @field:Json(name = "types")
    val types: List<TypeResponse>?
)

@JsonClass(generateAdapter = true)
data class TypeResponse(
    @field:Json(name = "name")
    val name: String?
)