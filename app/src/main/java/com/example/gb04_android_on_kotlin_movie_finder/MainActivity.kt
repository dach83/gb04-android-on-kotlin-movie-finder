package com.example.gb04_android_on_kotlin_movie_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ActivityMainBinding
import com.example.gb04_android_on_kotlin_movie_finder.ui.movie_card.MovieCardFragment
import com.example.gb04_android_on_kotlin_movie_finder.ui.movies_screen.MoviesScreenFragment

class MainActivity : AppCompatActivity(), MoviesScreenFragment.Controller {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesScreenFragment.newInstance())
                .commit()
        }
    }

    override fun showMovieCard(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieCardFragment.newInstance(movieId))
            .addToBackStack("")
            .commit()
    }
}