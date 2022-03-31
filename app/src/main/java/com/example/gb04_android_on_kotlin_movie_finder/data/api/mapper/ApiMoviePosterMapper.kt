package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.ApiConstants
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieListItem
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image
import javax.inject.Inject

class ApiMoviePosterMapper @Inject constructor() : ApiMapper<ApiMovieListItem, Poster> {
    override fun mapToDomain(apiEntity: ApiMovieListItem): Poster {
        return Poster(
            apiEntity.id,
            ContentType.MOVIE,
            apiEntity.title.orEmpty(),
            Image(apiEntity.posterPath.orEmpty())
        )
    }
}