package com.example.gb04_android_on_kotlin_movie_finder.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.presentation.movie.CompilationFragment
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CompilationFragment.tvShowsFragment())
                .commit()
        }
    }

}