package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieListItem
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowListItem
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import javax.inject.Inject

class ApiTvShowPosterMapper @Inject constructor() : ApiMapper<ApiTvShowListItem, Poster> {
    override fun mapToDomain(apiEntity: ApiTvShowListItem): Poster {
        return Poster.TvShowPoster(
            apiEntity.id,
            apiEntity.posterPath.orEmpty(),
            apiEntity.name
        )
    }
}