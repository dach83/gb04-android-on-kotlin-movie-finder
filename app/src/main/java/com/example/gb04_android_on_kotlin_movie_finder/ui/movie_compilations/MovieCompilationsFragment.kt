package com.example.gb04_android_on_kotlin_movie_finder.ui.movie_compilations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMovieCompilationsBinding
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.ResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.movieCategories
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.CompilationsAdapter
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.PosterAdapter

class MovieCompilationsFragment : Fragment(), CompilationsAdapter.Controller {

    private lateinit var viewModel: MovieCompilationsViewModel

    private var _binding: FragmentMovieCompilationsBinding? = null
    private val binding: FragmentMovieCompilationsBinding get() = _binding!!

    private lateinit var controller: Controller

    companion object {
        fun newInstance() = MovieCompilationsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Controller) {
            controller = context
        } else {
            throw IllegalStateException("Activity must implement MovieScreenFragment.Controller")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieCompilationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieCompilationsViewModel::class.java]

        val movieCompilationAdapter = CompilationsAdapter(movieCategories, this)
        binding.movieCompilationRecyclerView.adapter = movieCompilationAdapter

        viewModel.getMovieCompilation(*movieCategories)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBindPosterAdapter(
        category: Category,
        posterAdapter: PosterAdapter,
        binding: ItemCompilationBinding
    ) {
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
                }
            }
        }
    }

    override fun onClickSeeAll(category: Category) {
        Toast.makeText(context, "See all ${category.title} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onClickTryAgain(category: Category) {
        viewModel.getMovieCompilation(category)
    }

    override fun onClickPoster(poster: Poster) {
        controller.showMovieCard(poster.id)
    }

    interface Controller {
        fun showMovieCard(movieId: Int)
    }
}