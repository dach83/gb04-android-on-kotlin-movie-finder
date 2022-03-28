package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow

import com.google.gson.annotations.SerializedName

data class ApiTvShowListItem (
	@SerializedName("poster_path") val posterPath : String?,
	@SerializedName("id") val id : Int,

	@SerializedName("popularity") val popularity : Double,
	@SerializedName("backdrop_path") val backdropPath : String,
	@SerializedName("vote_average") val voteAverage : Double,
	@SerializedName("overview") val overview : String,
	@SerializedName("first_air_date") val firstAirDate : String,
	@SerializedName("origin_country") val originCountry : List<String>,
	@SerializedName("genre_ids") val genreIds : List<Int>,
	@SerializedName("original_language") val originalLanguage : String,
	@SerializedName("vote_count") val voteCount : Int,
	@SerializedName("name") val name : String,
	@SerializedName("original_name") val originalName : String
)