package com.example.gb04_android_on_kotlin_movie_finder.ui.movie_card

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMovieCardBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.ResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import java.lang.Error
import java.lang.RuntimeException

class MovieCardFragment : Fragment() {

    private lateinit var viewModel: MovieCardViewModel

    private var _binding: FragmentMovieCardBinding? = null
    private val binding: FragmentMovieCardBinding get() = _binding!!

    private var movieId: Int = UNDEFINED_ID

    companion object {
        private const val TAG = "@@@"
        private const val UNDEFINED_ID = 0
        private const val MOVIE_ID_KEY = "movie_id"

        fun newInstance(movieId: Int): MovieCardFragment {
            return MovieCardFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID_KEY, movieId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieCardViewModel::class.java]
        viewModel.movieCard.observe(viewLifecycleOwner) { responseState ->
            when (responseState) {
                is ResponseState.Success -> {
                    renderMovieCard(responseState.data)
                }
                is ResponseState.Error -> {
                    Toast.makeText(context, "Movie not found", Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading -> {}
            }
        }
        viewModel.getMovie(movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArguments() {
        val arguments = requireArguments()
        if (!arguments.containsKey(MOVIE_ID_KEY)) {
            throw RuntimeException("Param movie id is absent")
        }
        movieId = arguments.getInt(MOVIE_ID_KEY)
    }

    private fun renderMovieCard(movie: Movie) {
        binding.movieTitleTextView.text = movie.title
    }
}