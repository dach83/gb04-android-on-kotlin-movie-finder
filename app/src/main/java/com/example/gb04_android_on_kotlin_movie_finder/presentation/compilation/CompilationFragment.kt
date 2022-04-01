package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.App
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.*
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CompilationFragment : Fragment(), CompilationAdapter.Controller, PosterAdapter.Controller {

    private val args by navArgs<CompilationFragmentArgs>()
    private var _binding: FragmentCompilationBinding? = null
    private val binding: FragmentCompilationBinding get() = _binding!!
    private val viewModel: CompilationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
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
        // TODO(Не сохраняется позиция в горизонтальных списках при повороте экрана и при смене фрагмента')
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
            viewModel.requestCompilationData(compilation).collectLatest {
                viewModel.compilationDataReceived()
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
                // TODO ("При выключенном интернете ProgressBar не появляется")
                binding.initialLoadProgressBar.isVisible = isInitialLoading
            }
    }

    override fun onClickSeeAll(compilation: Compilation) {
        val action =
            CompilationFragmentDirections.actionCompilationFragmentToPosterFragment(compilation)
        findNavController().navigate(action)
    }

    override fun onClickPoster(poster: Poster) {
        val action = CompilationFragmentDirections.actionMoviesFragmentToDetailsFragment(
            poster.contentId,
            poster.contentType
        )
        findNavController().navigate(action)
    }

}