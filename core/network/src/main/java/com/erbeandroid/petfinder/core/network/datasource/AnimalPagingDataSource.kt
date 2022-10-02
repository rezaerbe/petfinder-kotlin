package com.erbeandroid.petfinder.core.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.erbeandroid.petfinder.core.network.model.AnimalResponse
import com.erbeandroid.petfinder.core.network.service.PetFinderService

class AnimalPagingDataSource(
    private val petFinderService: PetFinderService,
    private val type: String,
    private val breed: String
) : PagingSource<Int, AnimalResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimalResponse> {
        return try {
            val page = params.key ?: 1
            val limit = params.loadSize
            val response = petFinderService.getAnimals(type, breed, page, limit)
            LoadResult.Page(
                data = response.animals ?: emptyList(),
                prevKey = null,
                nextKey =
                if (page == response.pagination?.totalPages) null
                else page.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimalResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}