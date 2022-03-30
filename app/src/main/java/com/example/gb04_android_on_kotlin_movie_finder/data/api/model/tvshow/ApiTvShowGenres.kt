package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow

import com.google.gson.annotations.SerializedName

data class ApiTvShowGenres (
	@SerializedName("id") val id : Int?,
	@SerializedName("name") val name : String?
)