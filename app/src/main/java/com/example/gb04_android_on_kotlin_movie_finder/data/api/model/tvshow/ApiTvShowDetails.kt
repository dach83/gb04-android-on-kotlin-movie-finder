package com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow

import com.google.gson.annotations.SerializedName


data class ApiTvShowDetails(
	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String?,
	@SerializedName("overview") val overview: String?,
	@SerializedName("tagline") val tagline: String?,
	@SerializedName("backdrop_path") val backdropPath: String?,
	@SerializedName("poster_path") val posterPath: String?,

//	@SerializedName("created_by") val createdBy: List<ApiTvShowCreatedBy>?,
//	@SerializedName("episode_run_time") val episodeRunTime: List<Int>?,
//	@SerializedName("first_air_date") val firstAirDate: String?,
//	@SerializedName("genres") val genres: List<ApiTvShowGenres>?,
//	@SerializedName("homepage") val homepage: String?,
//	@SerializedName("in_production") val inProduction: Boolean?,
//	@SerializedName("languages") val languages: List<String>?,
//	@SerializedName("last_air_date") val lastAirDate: String?,
//	@SerializedName("last_episode_to_air") val lastEpisodeToAir: ApiTvShowLastEpisodeToAir?,
//	@SerializedName("next_episode_to_air") val nextEpisodeToAir: String?,
//	@SerializedName("networks") val networks: List<ApiTvShowNetworks>?,
//	@SerializedName("number_of_episodes") val numberOfEpisodes: Int?,
//	@SerializedName("number_of_seasons") val numberOfSeasons: Int?,
//	@SerializedName("origin_country") val originCountry: List<String>?,
//	@SerializedName("original_language") val originalLanguage: String?,
//	@SerializedName("original_name") val originalName: String?,
//	@SerializedName("popularity") val popularity: Double?,
//	@SerializedName("production_companies") val productionCompanies: List<ApiTvShowProductionCompanies>?,
//	@SerializedName("production_countries") val productionCountries: List<ApiTvShowProductionCountries>?,
//	@SerializedName("seasons") val seasons: List<ApiTvShowSeasons>?,
//	@SerializedName("spoken_languages") val spokenLanguages: List<ApiTvShowSpokenLanguages>?,
//	@SerializedName("status") val status: String?,
//	@SerializedName("type") val type: String?,
//	@SerializedName("vote_average") val vote_average: Double?,
//	@SerializedName("vote_count") val vote_count: Int?
)