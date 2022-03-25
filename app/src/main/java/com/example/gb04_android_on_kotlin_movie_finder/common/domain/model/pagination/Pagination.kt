package com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.pagination

data class Pagination(
    val currentPage: Int,
    val totalPages: Int
) {
    companion object {
        const val UNKNOWN_TOTAL = -1
    }

    val canLoadMore: Boolean
        get() = totalPages == UNKNOWN_TOTAL || currentPage < totalPages
}
