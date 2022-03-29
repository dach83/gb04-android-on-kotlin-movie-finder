package com.example.gb04_android_on_kotlin_movie_finder.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiService
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiTvShowPaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiTvShowPosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PaginatedPoster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val apiMoviePosterMapper: ApiMoviePosterMapper,
    private val apiMoviePaginationMapper: ApiMoviePaginationMapper,
    private val apiTvShowPosterMapper: ApiTvShowPosterMapper,
    private val apiTvShowPaginationMapper: ApiTvShowPaginationMapper
) : IRepository {

    override fun requestCompilation(compilation: Compilation): Flow<PagingData<Poster>> {
        val loader = when (compilation.contentType) {
            ContentType.MOVIE -> paginatedMoviePosterLoader(compilation)
            ContentType.TVSHOW -> paginatedTvShowPosterLoader(compilation)
        }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                PosterPagingSource(loader)
            }
        ).flow
    }

    private fun paginatedMoviePosterLoader(compilation: Compilation): PaginatedPosterLoader {
        return { page ->
            val path = ApiConstants.compilationPath(compilation)
            val apiMovieList = apiService.getMovieList(path, page)
            val posters = apiMovieList.results.map {
                apiMoviePosterMapper.mapToDomain(it)
            }
            val pagination = apiMoviePaginationMapper.mapToDomain(
                apiMovieList
            )
            PaginatedPoster(posters, pagination)
        }
    }

    private fun paginatedTvShowPosterLoader(compilation: Compilation): PaginatedPosterLoader {
        return { page ->
            val path = ApiConstants.compilationPath(compilation)
            val apiTvShowList = apiService.getTvShowList(path, page)
            val posters = apiTvShowList.results.map {
                apiTvShowPosterMapper.mapToDomain(it)
            }
            val pagination = apiTvShowPaginationMapper.mapToDomain(
                apiTvShowList
            )
            PaginatedPoster(posters, pagination)
        }
    }
}