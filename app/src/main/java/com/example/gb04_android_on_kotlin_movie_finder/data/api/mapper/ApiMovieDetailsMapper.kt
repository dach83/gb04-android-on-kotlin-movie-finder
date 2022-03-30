package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieDetails
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import javax.inject.Inject

class ApiMovieDetailsMapper @Inject constructor(): ApiMapper<ApiMovieDetails, Details> {
    override fun mapToDomain(apiEntity: ApiMovieDetails): Details {
        return Details(
            apiEntity.id,
            apiEntity.title.orEmpty(),
            apiEntity.tagline.orEmpty(),
            apiEntity.overview.orEmpty(),
            ApiConstants.imageUrl(apiEntity.posterPath),
            ApiConstants.imageUrl(apiEntity.backdropPath)
        )
    }

}