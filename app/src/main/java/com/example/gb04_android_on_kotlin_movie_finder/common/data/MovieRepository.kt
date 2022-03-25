package com.example.gb04_android_on_kotlin_movie_finder.common.data

import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.MovieApi
import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.mappers.ApiMovieMapper
import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.mappers.ApiPaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.Movie
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.pagination.PaginatedMovies
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val apiMovieMapper: ApiMovieMapper,
    private val apiPaginationMapper: ApiPaginationMapper
) : IMovieRepository {

    override suspend fun getMovieDetails(movieId: Int): Movie {
        val apiMovieDetails = movieApi.getDetails(movieId)
        return Movie(
            apiMovieDetails.id,
            apiMovieDetails.title
        )
    }

    override suspend fun getMoviesCompilation(
        compilation: MovieCompilation,
        page: Int
    ): PaginatedMovies {
        val apiMovieCompilation = when (compilation) {
            MovieCompilation.UPCOMING -> movieApi.getUpcoming(page)
            MovieCompilation.NOW_PLAYING -> movieApi.getNowPlaying(page)
            MovieCompilation.POPULAR -> movieApi.getPopular(page)
            MovieCompilation.TOP_RATED -> movieApi.getTopRated(page)
        }
        return PaginatedMovies(
            apiMovieCompilation.movies.map { apiMovieMapper.mapToDomain(it) },
            apiPaginationMapper.mapToDomain(apiMovieCompilation)
        )
    }
}