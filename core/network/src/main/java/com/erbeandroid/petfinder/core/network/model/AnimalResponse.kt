package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimalResponse(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "organization_id")
    val organizationId: String?,
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "species")
    val species: String?,
    @field:Json(name = "age")
    val age: String?,
    @field:Json(name = "gender")
    val gender: String?,
    @field:Json(name = "size")
    val size: String?,
    @field:Json(name = "coat")
    val coat: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "description")
    val description: String?
)