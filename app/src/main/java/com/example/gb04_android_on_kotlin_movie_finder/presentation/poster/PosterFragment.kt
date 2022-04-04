package com.example.gb04_android_on_kotlin_movie_finder.presentation.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentPosterBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.PosterFilter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PosterFragment : Fragment(), PosterAdapter.Controller {

    private val args by navArgs<PosterFragmentArgs>()

    private lateinit var filter: PosterFilter

    private var _binding: FragmentPosterBinding? = null
    private val binding: FragmentPosterBinding get() = _binding!!

    private val viewModel: PosterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFilter()
        setupSwipeRefresh()
        setupPosterAdapter()
        observeUiState()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSwipeRefresh() =
        binding.swipeRefreshLayout.setOnRefreshListener (viewModel::refreshUi)

    private fun setupFilter() {
        filter = try {
            args.filter
        } catch (e: Exception) {
            PosterFilter.FavoritesFilter // default filter
        }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.apply {
                posterLoadingProgressBar.isVisible = uiState.isLoading
                swipeRefreshLayout.isRefreshing = uiState.isRefreshing
            }
        }
    }

    private fun setupPosterAdapter() {
        val columns = resources.getInteger(R.integer.recycler_columns)
        val adapter = PosterAdapter(this)
        binding.posterRecyclerView.layoutManager = GridLayoutManager(context, columns)
        binding.posterRecyclerView.adapter = adapter
        observePosterFlow(adapter)
        observeRefreshUi(adapter)
        observeLoadingState(adapter)
    }

    private fun observeLoadingState(adapter: PosterAdapter) =
        adapter.addLoadStateListener {
            when (it.refresh) {
                !is LoadState.Loading -> viewModel.posterDataReceived()
                else -> Unit
            }
        }

    private fun observePosterFlow(adapter: PosterAdapter) {
        lifecycleScope.launchWhenStarted {
            viewModel.requestPosterData(filter).collectLatest {
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

    override fun onClickPoster(poster: Poster) {
        val action = PosterFragmentDirections.actionPosterFragmentToDetailsFragment(
            poster.contentId,
            poster.contentType
        )
        findNavController().navigate(action)
    }
}