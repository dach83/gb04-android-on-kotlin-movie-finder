package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.compilation

import com.google.gson.annotations.SerializedName

data class ApiMovieCompilationDates(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
)