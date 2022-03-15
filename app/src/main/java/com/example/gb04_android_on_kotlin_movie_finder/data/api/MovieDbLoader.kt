package com.example.gb04_android_on_kotlin_movie_finder.data.api

import android.os.Handler
import com.example.gb04_android_on_kotlin_movie_finder.BuildConfig
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.getLines
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MovieDbLoader(private val listener: MovieLoaderListener) {

    fun loadMoviesCompilation(category: Category) {
        val uri = categoryURL(category)
        val handler = Handler()
        Thread {
            lateinit var urlConnection: HttpURLConnection
            try {
                urlConnection = uri.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 10000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val compilationApi: CompilationApi =
                    Gson().fromJson(bufferedReader.getLines(), CompilationApi::class.java)
                val movies = MovieMapperApiToEntity.transformList(compilationApi.results)
                handler.post { listener.onLoaded(category, movies) }
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onFailed(category, e)
            } finally {
                urlConnection.disconnect()
            }
        }.start()
    }

    private fun categoryURL(category: Category): URL {
        val categoryUri = when (category) {
            Category.UPCOMING -> "upcoming"
            Category.NOW_PLAYING -> "now_playing"
            Category.POPULAR -> "popular"
            Category.TOP_RATED -> "top_rated"
        }
        return URL("https://api.themoviedb.org/3/movie/$categoryUri?api_key=${BuildConfig.MOVIE_DB_API_KEY}")
    }

    interface MovieLoaderListener {
        fun onLoaded(category: Category, movies: List<Movie>)
        fun onFailed(category: Category, throwable: Throwable)
    }
}