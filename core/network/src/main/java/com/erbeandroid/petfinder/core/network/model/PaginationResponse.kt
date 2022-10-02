package com.erbeandroid.petfinder.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    @field:Json(name = "count_per_page")
    val countPerPage: Int?,
    @field:Json(name = "total_count")
    val totalCount: Int?,
    @field:Json(name = "current_page")
    val currentPage: Int?,
    @field:Json(name = "total_pages")
    val totalPages: Int?
)