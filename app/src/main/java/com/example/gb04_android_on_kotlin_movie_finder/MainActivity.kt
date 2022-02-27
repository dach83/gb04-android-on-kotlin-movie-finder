package com.example.gb04_android_on_kotlin_movie_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ActivityMainBinding
import com.example.gb04_android_on_kotlin_movie_finder.ui.movie_card.MovieCardFragment
import com.example.gb04_android_on_kotlin_movie_finder.ui.movie_compilations.MovieCompilationsFragment

class MainActivity : AppCompatActivity(), MovieCompilationsFragment.Controller {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieCompilationsFragment.newInstance())
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