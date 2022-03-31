package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowDetails
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image
import javax.inject.Inject

class ApiTvShowDetailsMapper @Inject constructor() : ApiMapper<ApiTvShowDetails, Details> {
    override fun mapToDomain(apiEntity: ApiTvShowDetails): Details {
        return Details(
            apiEntity.id,
            apiEntity.name.orEmpty(),
            apiEntity.tagline.orEmpty(),
            apiEntity.overview.orEmpty(),
            Image(apiEntity.posterPath.orEmpty()),
            Image(apiEntity.backdropPath.orEmpty())
        )
    }

}