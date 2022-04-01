package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow

import com.google.gson.annotations.SerializedName

data class ApiTvShowList(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ApiTvShowListItem>,
    @SerializedName("total_pages") val totalPages: Int,
//    @SerializedName("total_results") val totalResults: Int
)