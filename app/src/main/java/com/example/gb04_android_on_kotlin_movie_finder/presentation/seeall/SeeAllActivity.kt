package com.example.gb04_android_on_kotlin_movie_finder.presentation.seeall

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ActivitySeeAllBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SeeAllActivity : AppCompatActivity(), PosterAdapter.Controller {

    private val args by navArgs<SeeAllActivityArgs>()

    private lateinit var binding: ActivitySeeAllBinding

    private val viewModel: SeeAllViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        requestCompilation()
        setupPosterAdapter()
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::requestRefresh)
    }

    private fun requestCompilation() {
        viewModel.requestCompilation(args.compilation)
    }

    private fun setupPosterAdapter() {
        val adapter = PosterAdapter(this)
        binding.seeAllRecyclerView.adapter = adapter
        observeCompilation(adapter)
        observeRefresh(adapter)
    }

    private fun observeRefresh(adapter: PosterAdapter) {
        viewModel.refresh.observe(this) {
            adapter.refresh()
        }
    }

    private fun observeCompilation(adapter: PosterAdapter) {
        lifecycleScope.launchWhenStarted {
            viewModel.compilation.collectLatest {
                adapter.submitData(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(this, poster.title, Toast.LENGTH_SHORT).show() // TODO
    }
}