package com.example.gb04_android_on_kotlin_movie_finder.common.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMovieReviewBinding

class MoviesHomeFragment : Fragment() {

    private var _binding: FragmentMovieReviewBinding? = null
    private val binding: FragmentMovieReviewBinding = _binding!!

    private val viewModel: MoviesHomeViewModel by viewModels()

    companion object {
        fun newInstance() = MoviesHomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}