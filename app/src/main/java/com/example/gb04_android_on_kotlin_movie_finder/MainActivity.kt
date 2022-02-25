package com.example.gb04_android_on_kotlin_movie_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gb04_android_on_kotlin_movie_finder.ui.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesFragment.newInstance())
                .commitNow()
        }
    }
}