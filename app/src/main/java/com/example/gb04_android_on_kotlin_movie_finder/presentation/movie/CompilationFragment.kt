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
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.moviesCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.tvShowsCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompilationFragment : Fragment(), CompilationAdapter.Controller, PosterAdapter.Controller {

    private var _binding: FragmentCompilationBinding? = null
    private val binding: FragmentCompilationBinding get() = _binding!!

    private val viewModel: CompilationViewModel by viewModels()

    private lateinit var showMode: ShowMode
    private lateinit var compilations: List<Compilation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompilationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestCompilations()
        setupCompilationAdapter()
    }

    private fun parseArguments() {
        showMode = requireArguments().getSerializable(MODE_KEY) as ShowMode
    }

    private fun requestCompilations() {
        compilations = when (showMode) {
            ShowMode.MOVIES -> moviesCompilation
            ShowMode.TVSHOWS -> tvShowsCompilation
        }
        compilations.forEach(viewModel::requestCompilation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCompilationAdapter() {
        val adapter = CompilationAdapter(compilations, this)
        binding.compilationRecyclerView.adapter = adapter
    }

    override fun setupPosterAdapter(
        compilation: Compilation,
        recyclerView: RecyclerView
    ) {
        val adapter = PosterAdapter(this)
        recyclerView.adapter = adapter
        observePosterCompilation(compilation, adapter)
    }

    private fun observePosterCompilation(
        compilation: Compilation,
        posterAdapter: PosterAdapter
    ) {
        lifecycleScope.launch {
            viewModel.compilations[compilation]?.collectLatest(posterAdapter::submitData)
        }
    }

    override fun onClickSeeAll(compilation: Compilation) {
        Toast.makeText(context, compilation.title, Toast.LENGTH_SHORT).show()
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, poster.title, Toast.LENGTH_SHORT).show()
    }

    enum class ShowMode {
        MOVIES,
        TVSHOWS
    }

    companion object {

        private const val MODE_KEY = "mode"

        private fun getInstance(showMode: ShowMode) = CompilationFragment().apply {
            arguments = Bundle().apply {
                putSerializable(MODE_KEY, showMode)
            }
        }

        fun moviesFragment() = getInstance(ShowMode.MOVIES)
        fun tvShowsFragment() = getInstance(ShowMode.TVSHOWS)
    }
}