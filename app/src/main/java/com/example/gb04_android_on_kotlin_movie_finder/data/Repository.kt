package com.example.gb04_android_on_kotlin_movie_finder.data

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.preference.PreferenceManager
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiService
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiMoviePosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiTvShowPaginationMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper.ApiTvShowPosterMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.db.daos.DetailsDao
import com.example.gb04_android_on_kotlin_movie_finder.data.db.model.DbDetails
import com.example.gb04_android_on_kotlin_movie_finder.data.mapper.MovieDetailsMapper
import com.example.gb04_android_on_kotlin_movie_finder.data.mapper.TvShowDetailsMapper
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Settings
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PaginatedPoster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val app: Application,
    private val apiService: ApiService,
    private val detailsDao: DetailsDao,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val tvShowDetailsMapper: TvShowDetailsMapper,
    private val apiMoviePosterMapper: ApiMoviePosterMapper,
    private val apiMoviePaginationMapper: ApiMoviePaginationMapper,
    private val apiTvShowPosterMapper: ApiTvShowPosterMapper,
    private val apiTvShowPaginationMapper: ApiTvShowPaginationMapper
) : IRepository {

    override suspend fun storeDetails(details: Details) {
        val dbDetails = DbDetails.fromDomain(details)
        if (dbDetails.isEmpty()) {
            detailsDao.deleteDetails(dbDetails)
        } else {
            detailsDao.insertDetails(dbDetails)
        }
    }

    override suspend fun requestDetails(id: Int, contentType: ContentType): Details =
        when (contentType) {
            ContentType.MOVIE -> requestMovieDetails(id, contentType)
            ContentType.TVSHOW -> requestTvShowDetails(id, contentType)
        }

    private suspend fun requestMovieDetails(id: Int, contentType: ContentType): Details =
        coroutineScope {
            val apiDetails = async(Dispatchers.IO) { apiService.getMovieDetails(id) }
            val dbDetails = async(Dispatchers.IO) { detailsDao.getDetails(id, contentType) }
            movieDetailsMapper.mapToDomain(apiDetails.await(), dbDetails.await())
        }

    private suspend fun requestTvShowDetails(id: Int, contentType: ContentType): Details =
        coroutineScope {
            val apiDetails = async { apiService.getTvShowDetails(id) }
            val dbDetails = async { detailsDao.getDetails(id, contentType) }
            tvShowDetailsMapper.mapToDomain(apiDetails.await(), dbDetails.await())
        }

    override fun requestCompilation(compilation: Compilation): Flow<PagingData<Poster>> {
        val loader = when (compilation.contentType) {
            ContentType.MOVIE -> paginatedMoviePosterLoader(compilation)
            ContentType.TVSHOW -> paginatedTvShowPosterLoader(compilation)
        }
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PosterPagingSource(loader) }
        ).flow
    }

    override fun requestFavorites(): Flow<PagingData<Poster>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { detailsDao.favoritesPagingSource() }
    )
        .flow
        .map { pagingData ->
            pagingData.map { dbDetails ->
                requestDetails(dbDetails.contentId, dbDetails.contentType).toPoster()
            }
        }

    private fun paginatedMoviePosterLoader(compilation: Compilation): PaginatedPosterLoader =
        { page ->
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

    private fun paginatedTvShowPosterLoader(compilation: Compilation): PaginatedPosterLoader =
        { page ->
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

    override fun loadSettings(): Settings {
        val prefs = PreferenceManager.getDefaultSharedPreferences(app)
        return Settings(
            enabledAdult = prefs.getBoolean(app.getString(R.string.adult_enabled), false)
        )
    }
}