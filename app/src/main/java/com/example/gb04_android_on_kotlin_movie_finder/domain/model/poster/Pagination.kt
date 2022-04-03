package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

data class Pagination(
    val currentPage: Int,
    val totalPages: Int
) {
    val prevPageOrNull = if (currentPage > 1) currentPage.minus(1) else null
    val nextPageOrNull = if (currentPage < totalPages) currentPage.plus(1) else null
}
