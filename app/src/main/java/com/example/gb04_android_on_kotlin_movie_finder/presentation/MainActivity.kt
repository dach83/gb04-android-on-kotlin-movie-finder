package com.example.gb04_android_on_kotlin_movie_finder.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setupToolbar()
        setupBottomNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)

    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isMainFragment = when (destination.id) {
                R.id.movies_fragment,
                R.id.tvshows_fragment -> true
                else -> false
            }
            binding.toolbar.isVisible = isMainFragment
            binding.bottomNav.isVisible = isMainFragment
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
//        supportActionBar?.let {
//            it.setDisplayShowHomeEnabled(true)
//            it.setDisplayHomeAsUpEnabled(true)
//        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setupWithNavController(navController)
    }

}