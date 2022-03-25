package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.detail

import com.google.gson.annotations.SerializedName


data class ApiSpokenLanguages(
    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("name") val name: String
)