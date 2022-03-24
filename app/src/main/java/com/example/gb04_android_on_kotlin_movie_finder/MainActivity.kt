package com.example.gb04_android_on_kotlin_movie_finder

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ActivityMainBinding
import com.example.gb04_android_on_kotlin_movie_finder.ui.NetworkChangeReceiver
import com.example.gb04_android_on_kotlin_movie_finder.ui.movie_card.MovieCardFragment
import com.example.gb04_android_on_kotlin_movie_finder.ui.movie_compilations.MovieCompilationsFragment


class MainActivity : AppCompatActivity(), MovieCompilationsFragment.Controller {

    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieCompilationsFragment.newInstance())
                .commit()
        }

        receiver = NetworkChangeReceiver()
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun showMovieCard(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieCardFragment.newInstance(movieId))
            .addToBackStack("")
            .commit()
    }
}