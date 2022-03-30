package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow

import com.google.gson.annotations.SerializedName

data class ApiTvShowSpokenLanguages(
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("name") val name: String?
)