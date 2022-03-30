package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.movieCompilations
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.tvShowCompilations
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CompilationFragment : Fragment(), CompilationAdapter.Controller, PosterAdapter.Controller {

    private val args by navArgs<CompilationFragmentArgs>()

    private var _binding: FragmentCompilationBinding? = null
    private val binding: FragmentCompilationBinding get() = _binding!!

    private val viewModel: CompilationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompilationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeRefresh()
        setupCompilationAdapter()
        observeInitialLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::refreshUi)
        viewModel.uiState
            .map { it.isRefreshing }
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) {
                binding.swipeRefreshLayout.isRefreshing = it
            }
    }

    private fun setupCompilationAdapter() {
        val compilations = when (args.contentType) {
            ContentType.MOVIE -> movieCompilations
            ContentType.TVSHOW -> tvShowCompilations
        }
        val adapter = CompilationAdapter(compilations, this)
        binding.compilationRecyclerView.adapter = adapter
    }

    override fun setupPosterAdapter(
        compilation: Compilation,
        recyclerView: RecyclerView
    ) {
        val adapter = PosterAdapter(this)
        recyclerView.adapter = adapter
        observePosterFlow(compilation, adapter)
        observeRefreshUi(adapter)
    }

    private fun observePosterFlow(
        compilation: Compilation,
        adapter: PosterAdapter
    ) {
        lifecycleScope.launchWhenStarted {
            viewModel.requestCompilationFlow(compilation).collectLatest {
                viewModel.compilationFlowReceived()
                adapter.submitData(it)
            }
        }
    }

    private fun observeRefreshUi(adapter: PosterAdapter) = viewModel.uiState
        .map { it.isRefreshing }
        .distinctUntilChanged()
        .observe(viewLifecycleOwner) { isRefreshing ->
            if (isRefreshing) {
                adapter.refresh()
            }
        }

    private fun observeInitialLoad() {
        viewModel.uiState
            .map { it.isInitialLoading }
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) { isInitialLoading ->
                binding.initialLoadProgressBar.isVisible = isInitialLoading
            }
    }

    override fun onClickSeeAll(compilation: Compilation) {
        val action =
            CompilationFragmentDirections.actionCompilationFragmentToPosterFragment(compilation)
        findNavController().navigate(action)
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, poster.title, Toast.LENGTH_SHORT).show() // TODO
    }

}