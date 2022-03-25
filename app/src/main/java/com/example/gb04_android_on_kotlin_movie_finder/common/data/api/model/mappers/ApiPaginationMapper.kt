package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.mappers

import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.compilation.ApiMovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.pagination.Pagination
import javax.inject.Inject

class ApiPaginationMapper @Inject constructor(): ApiMapper<ApiMovieCompilation, Pagination> {
    override fun mapToDomain(apiEntity: ApiMovieCompilation): Pagination {
        return Pagination(
            apiEntity.page,
            apiEntity.totalPages
        )
    }
}