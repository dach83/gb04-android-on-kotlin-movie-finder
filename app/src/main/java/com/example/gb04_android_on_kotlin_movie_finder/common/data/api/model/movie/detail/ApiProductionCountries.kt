package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.detail

import com.google.gson.annotations.SerializedName

data class ApiProductionCountries(
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name") val name: String
)