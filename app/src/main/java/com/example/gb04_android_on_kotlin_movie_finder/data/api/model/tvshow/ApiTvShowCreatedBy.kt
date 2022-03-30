package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow

import com.google.gson.annotations.SerializedName

data class ApiTvShowCreatedBy(
	@SerializedName("id") val id: Int?,
	@SerializedName("credit_id") val creditId: String?,
	@SerializedName("name") val name: String?,
	@SerializedName("gender") val gender: Int?,
	@SerializedName("profile_path") val profilePath: String?
)