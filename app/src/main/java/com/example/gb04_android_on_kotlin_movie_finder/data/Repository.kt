package com.example.gb04_android_on_kotlin_movie_finder.data

import android.app.Application
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.preference.PreferenceManager
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiService
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.*
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Settings
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PaginatedPoster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val app: Application,
    private val apiService: ApiService,
    private val apiMovieDetailsMapper: ApiMovieDetailsMapper,
    private val apiTvShowDetailsMapper: ApiTvShowDetailsMapper,
    private val apiMoviePosterMapper: ApiMoviePosterMapper,
    private val apiMoviePaginationMapper: ApiMoviePaginationMapper,
    private val apiTvShowPosterMapper: ApiTvShowPosterMapper,
    private val apiTvShowPaginationMapper: ApiTvShowPaginationMapper
) : IRepository {

    override suspend fun requestDetails(id: Int, contentType: ContentType) = when (contentType) {
        ContentType.MOVIE -> apiMovieDetailsMapper.mapToDomain(apiService.getMovieDetails(id))
        ContentType.TVSHOW -> apiTvShowDetailsMapper.mapToDomain(apiService.getTvShowDetails(id))
    }

    override fun requestCompilation(compilation: Compilation): Flow<PagingData<Poster>> {
        val loader = when (compilation.contentType) {
            ContentType.MOVIE -> paginatedMoviePosterLoader(compilation)
            ContentType.TVSHOW -> paginatedTvShowPosterLoader(compilation)
        }
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

    override fun loadSettings(): Settings {
        val prefs = PreferenceManager.getDefaultSharedPreferences(app)
        return Settings(
            enabledAdult = prefs.getBoolean(app.getString(R.string.adult_enabled), false)
        )
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