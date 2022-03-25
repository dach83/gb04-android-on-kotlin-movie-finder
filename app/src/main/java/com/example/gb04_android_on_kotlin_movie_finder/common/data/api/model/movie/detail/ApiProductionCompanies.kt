package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.detail

import com.google.gson.annotations.SerializedName

data class ApiProductionCompanies(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
)