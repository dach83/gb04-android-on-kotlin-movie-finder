package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieList
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Pagination
import javax.inject.Inject

class ApiMoviePaginationMapper @Inject constructor() : ApiMapper<ApiMovieList, Pagination> {
    override fun mapToDomain(apiEntity: ApiMovieList): Pagination {
        return Pagination(
            apiEntity.page,
            apiEntity.totalPages
        )
    }
}