package com.example.gb04_android_on_kotlin_movie_finder.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gb04_android_on_kotlin_movie_finder.data.api.MovieDbApi
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PaginatedPoster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieDbApi: MovieDbApi,
    private val apiMoviePosterMapper: ApiMoviePosterMapper,
    private val apiMoviePaginationMapper: ApiMoviePaginationMapper
) : IRepository {

    override fun getMovieCompilation(compilation: MovieCompilation): Flow<PagingData<Poster>> {
        val loader = getPaginatedMoviePosterLoader(compilation)
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PosterPagingSource(loader)
            }
        ).flow
    }

    private fun getPaginatedMoviePosterLoader(compilation: MovieCompilation): PaginatedPosterLoader {
        return { page ->
            val apiMovieList = when(compilation) {
                MovieCompilation.UPCOMING -> movieDbApi.getUpcomingMovies(page)
                MovieCompilation.NOW_PLAYING -> movieDbApi.getNowPlayingMovies(page)
                MovieCompilation.POPULAR -> movieDbApi.getPopularMovies(page)
                MovieCompilation.TOP_RATED -> movieDbApi.getTopRatedMovies(page)
            }
            val posters = apiMovieList.results.map {
                apiMoviePosterMapper.mapToDomain(it)
            }
            val pagination = apiMoviePaginationMapper.mapToDomain(
                apiMovieList
            )
            PaginatedPoster(posters, pagination)
        }
    }
}