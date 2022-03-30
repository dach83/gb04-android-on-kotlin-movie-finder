package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie

import com.google.gson.annotations.SerializedName

data class ApiMovieDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,

//    @SerializedName("adult") val adult: Boolean,
//    @SerializedName("belongs_to_collection") val belongsToCollection: String?,
//    @SerializedName("budget") val budget: Int,
//    @SerializedName("genres") val genres: List<ApiMovieGenres>,
//    @SerializedName("homepage") val homepage: String?,
//    @SerializedName("imdb_id") val imdbId: String?,
//    @SerializedName("original_language") val originalLanguage: String,
//    @SerializedName("original_title") val originalTitle: String,
//    @SerializedName("popularity") val popularity: Double,
//    @SerializedName("production_companies") val productionCompanies: List<ApiMovieProductionCompanies>,
//    @SerializedName("production_countries") val productionCountries: List<ApiMovieProductionCountries>,
//    @SerializedName("release_date") val releaseDate: String,
//    @SerializedName("revenue") val revenue: Int,
//    @SerializedName("runtime") val runtime: Int?,
//    @SerializedName("spoken_languages") val spokenLanguages: List<ApiMovieSpokenLanguages>,
//    @SerializedName("status") val status: String,
//    @SerializedName("video") val video: Boolean,
//    @SerializedName("vote_average") val voteAverage: Double,
//    @SerializedName("vote_count") val voteCount: Int
)