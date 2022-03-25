package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.compilation

import com.google.gson.annotations.SerializedName

data class ApiMovieCompilation(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<ApiMovieCompilationItem>,
    @SerializedName("dates") val dates: ApiMovieCompilationDates?,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)