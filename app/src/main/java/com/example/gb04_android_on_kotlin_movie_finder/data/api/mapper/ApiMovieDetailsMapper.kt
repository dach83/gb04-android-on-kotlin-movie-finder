package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieDetails
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image
import javax.inject.Inject

class ApiMovieDetailsMapper @Inject constructor() : ApiMapper<ApiMovieDetails, Details> {
    override fun mapToDomain(apiEntity: ApiMovieDetails): Details = Details(
        apiEntity.id,
        apiEntity.adult,
        apiEntity.title.orEmpty(),
        apiEntity.tagline.orEmpty(),
        apiEntity.overview.orEmpty(),
        Image(apiEntity.posterPath.orEmpty()),
        Image(apiEntity.backdropPath.orEmpty())
    )

}