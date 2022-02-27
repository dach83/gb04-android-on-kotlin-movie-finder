package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMoviesBinding
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.ResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.movieCategories
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.CompilationsAdapter
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.PosterAdapter
import com.google.android.material.snackbar.Snackbar

class MoviesFragment : Fragment(), CompilationsAdapter.Controller {

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding get() = _binding!!

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        val movieCompilationAdapter = CompilationsAdapter(movieCategories, this)
        binding.movieCompilationRecyclerView.adapter = movieCompilationAdapter

        viewModel.requestMovieCompilations(*movieCategories)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBindPosterAdapter(category: Category, posterAdapter: PosterAdapter, binding: ItemCompilationBinding) {
        viewModel.movieCompilations[category]?.observe(viewLifecycleOwner) { responseState ->
            when (responseState) {
                is ResponseState.Success -> {
                    val posters = responseState.data.map { Poster(it.id) }
                    posterAdapter.submitList(posters)
                    binding.loadingProgressBar.visibility = View.GONE
                    binding.tryAgainTextView.visibility = View.GONE
                    binding.seeAllTextView.visibility = View.VISIBLE
                }
                is ResponseState.Loading -> {
                    binding.tryAgainTextView.visibility = View.GONE
                    binding.loadingProgressBar.visibility = View.VISIBLE
                    binding.seeAllTextView.visibility = View.INVISIBLE
                }
                is ResponseState.Error -> {
                    binding.loadingProgressBar.visibility = View.GONE
                    binding.tryAgainTextView.visibility = View.VISIBLE
//                    Snackbar
//                        .make(binding.posterRecyclerView, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
//                        .setAction(getString(R.string.try_again)) { viewModel.requestMovieCompilations(category) }
//                        .show()
                }
            }
        }
    }

    override fun onClickSeeAll(category: Category) {
        Toast.makeText(context, "See all ${category.title} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onClickTryAgain(category: Category) {
        viewModel.requestMovieCompilations(category)
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, "$poster clicked", Toast.LENGTH_SHORT).show()
    }

}