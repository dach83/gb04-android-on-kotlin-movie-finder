package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowListItem
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import javax.inject.Inject

class ApiTvShowPosterMapper @Inject constructor() : ApiMapper<ApiTvShowListItem, Poster> {
    override fun mapToDomain(apiEntity: ApiTvShowListItem): Poster {
        return Poster(
            apiEntity.id,
            ContentType.TVSHOW,
            ApiConstants.imageUrl(apiEntity.posterPath),
            apiEntity.name.orEmpty()
        )
    }
}