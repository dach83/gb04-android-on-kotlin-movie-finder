package com.example.gb04_android_on_kotlin_movie_finder.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PaginatedPoster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster

typealias PaginatedPosterLoader = suspend (page: Int) -> PaginatedPoster

class PosterPagingSource(
    private val paginatedPosterLoader: PaginatedPosterLoader
) : PagingSource<Int, Poster>() {

    override fun getRefreshKey(state: PagingState<Int, Poster>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Poster> {
        return try {
            val page = params.key ?: 1
            val paginatedPoster = paginatedPosterLoader(page)
            LoadResult.Page(
                data = paginatedPoster.posters,
                prevKey = paginatedPoster.pagination.prevPageOrNull,
                nextKey = paginatedPoster.pagination.nextPageOrNull
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}