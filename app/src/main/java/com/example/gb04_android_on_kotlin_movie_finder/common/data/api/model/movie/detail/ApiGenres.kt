package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.detail

import com.google.gson.annotations.SerializedName

data class ApiGenres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)