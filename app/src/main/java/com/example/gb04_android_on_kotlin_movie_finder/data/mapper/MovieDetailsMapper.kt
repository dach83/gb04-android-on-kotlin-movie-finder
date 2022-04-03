package com.example.gb04_android_on_kotlin_movie_finder.data.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieDetails
import com.example.gb04_android_on_kotlin_movie_finder.data.db.model.DbDetails
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {
    fun mapToDomain(apiEntity: ApiMovieDetails, dbEntity: DbDetails?): Details = Details(
        apiEntity.id,
        ContentType.MOVIE,
        apiEntity.adult,
        apiEntity.title.orEmpty(),
        apiEntity.tagline.orEmpty(),
        apiEntity.overview.orEmpty(),
        Image(apiEntity.posterPath.orEmpty()),
        Image(apiEntity.backdropPath.orEmpty()),
        dbEntity?.userReview.orEmpty()
    )
}