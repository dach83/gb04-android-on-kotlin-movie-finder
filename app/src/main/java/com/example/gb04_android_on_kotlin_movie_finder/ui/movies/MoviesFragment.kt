package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentMoviesBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.movieCategories
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.CompilationsAdapter
import com.example.gb04_android_on_kotlin_movie_finder.ui.adapter.PosterAdapter

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBindPosterAdapter(category: Category, posterAdapter: PosterAdapter) {
        viewModel.movieCompilations[category]?.observe(viewLifecycleOwner) { movies ->
            val posters = movies.map { Poster(it.id) }
            posterAdapter.submitList(posters)
        }
    }

    override fun onClickSeeAll(category: Category) {
        Toast.makeText(context, "See all ${category.title} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, "$poster clicked", Toast.LENGTH_SHORT).show()
    }

}