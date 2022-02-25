package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMoviesBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.movieCategories
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.CompilationAdapter
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.PosterAdapter
import kotlin.math.log

class MoviesFragment : Fragment() {

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
        binding.movieCompilationRecyclerView.adapter =
            CompilationAdapter(movieCategories) { category, adapter ->
                viewModel.getPosterList(category).observe(viewLifecycleOwner) { movies ->
                    val posterList = movies.map { Poster(it.id, it.title) }
                    adapter.submitList(posterList)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}