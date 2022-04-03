package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowListItem
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image
import javax.inject.Inject

class ApiTvShowPosterMapper @Inject constructor() : ApiMapper<ApiTvShowListItem, Poster> {
    override fun mapToDomain(apiEntity: ApiTvShowListItem): Poster = Poster(
        apiEntity.id,
        ContentType.TVSHOW,
        apiEntity.name.orEmpty(),
        false,
        Image(apiEntity.posterPath.orEmpty())
    )
}