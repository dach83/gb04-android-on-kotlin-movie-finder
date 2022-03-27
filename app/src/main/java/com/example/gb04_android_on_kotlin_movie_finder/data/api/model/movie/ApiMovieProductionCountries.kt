package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie

import com.google.gson.annotations.SerializedName

data class ApiMovieProductionCountries(
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name") val name: String
)