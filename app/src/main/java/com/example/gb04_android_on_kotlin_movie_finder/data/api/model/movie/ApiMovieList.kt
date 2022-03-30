package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie

import com.google.gson.annotations.SerializedName

data class ApiMovieList(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ApiMovieListItem>,
    @SerializedName("total_pages") val totalPages: Int,
//    @SerializedName("total_results") val totalResults: Int
)