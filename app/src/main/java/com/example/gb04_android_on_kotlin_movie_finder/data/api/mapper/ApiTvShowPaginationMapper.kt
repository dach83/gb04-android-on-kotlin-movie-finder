package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowList
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Pagination
import javax.inject.Inject

class ApiTvShowPaginationMapper @Inject constructor() : ApiMapper<ApiTvShowList, Pagination> {
    override fun mapToDomain(apiEntity: ApiTvShowList): Pagination = Pagination(
        apiEntity.page,
        apiEntity.totalPages
    )
}