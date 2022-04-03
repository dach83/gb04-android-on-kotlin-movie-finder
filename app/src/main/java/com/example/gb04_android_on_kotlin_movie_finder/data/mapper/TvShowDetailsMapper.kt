package com.example.gb04_android_on_kotlin_movie_finder.data.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowDetails
import com.example.gb04_android_on_kotlin_movie_finder.data.db.model.DbDetails
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image
import javax.inject.Inject

class TvShowDetailsMapper @Inject constructor() {
    fun mapToDomain(apiEntity: ApiTvShowDetails, dbEntity: DbDetails?): Details = Details(
        apiEntity.id,
        ContentType.TVSHOW,
        false,
        apiEntity.name.orEmpty(),
        apiEntity.tagline.orEmpty(),
        apiEntity.overview.orEmpty(),
        Image(apiEntity.posterPath.orEmpty()),
        Image(apiEntity.backdropPath.orEmpty()),
        dbEntity?.userReview.orEmpty(),
        dbEntity?.favourites ?: false,
    )

}