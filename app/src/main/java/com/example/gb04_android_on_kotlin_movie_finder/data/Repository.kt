package com.example.gb04_android_on_kotlin_movie_finder.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gb04_android_on_kotlin_movie_finder.data.api.MovieDbApi
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiTvShowPaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiTvShowPosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation.MoviesCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation.MoviesCompilation.*
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation.TvShowsCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation.TvShowsCompilation.*
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PaginatedPoster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieDbApi: MovieDbApi,
    private val apiMoviePosterMapper: ApiMoviePosterMapper,
    private val apiMoviePaginationMapper: ApiMoviePaginationMapper,
    private val apiTvShowPosterMapper: ApiTvShowPosterMapper,
    private val apiTvShowPaginationMapper: ApiTvShowPaginationMapper
) : IRepository {

    override fun requestCompilation(compilation: Compilation): Flow<PagingData<Poster>> {
        val loader = when (compilation) {
            is MoviesCompilation -> paginatedMoviePosterLoader(compilation)
            is TvShowsCompilation -> paginatedTvShowPosterLoader(compilation)
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

    private fun paginatedMoviePosterLoader(compilation: MoviesCompilation): PaginatedPosterLoader {
        return { page ->
            val apiMovieList = when (compilation) {
                NowPlayingMovies -> movieDbApi.getNowPlayingMovies(page)
                PopularMovies -> movieDbApi.getPopularMovies(page)
                TopRatedMovies -> movieDbApi.getTopRatedMovies(page)
                UpcomingMovies -> movieDbApi.getUpcomingMovies(page)
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

    private fun paginatedTvShowPosterLoader(compilation: TvShowsCompilation): PaginatedPosterLoader {
        return { page ->
            val apiTvShowList = when (compilation) {
                AiringTodayTvShows -> movieDbApi.getAiringTodayTvShows(page)
                CurrentlyAiringTvShows -> movieDbApi.getCurrentlyAiringTvShows(page)
                PopularTvShows -> movieDbApi.getPopularTvShows(page)
                TopRatedTvShows -> movieDbApi.getTopRatedTvShows(page)
            }
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