package com.example.gb04_android_on_kotlin_movie_finder.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMovieMainBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieMainFragment : Fragment(), MovieCompilationAdapter.Controller, PosterAdapter.Controller {

    private var _binding: FragmentMovieMainBinding? = null
    private val binding: FragmentMovieMainBinding get() = _binding!!

    private val viewModel: MovieMainViewModel by viewModels()

    companion object {
        fun newInstance() = MovieMainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieCompilationAdapter(this)
        binding.movieCompilationRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observePosterCompilation(
        compilation: MovieCompilation,
        posterAdapter: PosterAdapter
    ) {
        lifecycleScope.launch {
            viewModel.postersFlow[compilation]?.collectLatest(posterAdapter::submitData)
        }
    }

    override fun setupPosterAdapter(
        compilation: MovieCompilation,
        recyclerView: RecyclerView
    ) {
        val posterAdapter = PosterAdapter(this)
        observePosterCompilation(compilation, posterAdapter)
        recyclerView.adapter = posterAdapter
    }

    override fun onClickSeeAll(compilation: MovieCompilation) {
        Toast.makeText(context, compilation.title, Toast.LENGTH_SHORT).show()
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, poster.title, Toast.LENGTH_SHORT).show()
    }
}