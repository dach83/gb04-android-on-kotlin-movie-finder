package com.example.gb04_android_on_kotlin_movie_finder.presentation.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentPosterBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PosterFragment : Fragment(), PosterAdapter.Controller {

    private val args by navArgs<PosterFragmentArgs>()

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
        setupPosterAdapter()
        setupSwipeRefresh()
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

    private fun setupPosterAdapter() {
        val columns = resources.getInteger(R.integer.recycler_columns)
        val adapter = PosterAdapter(this)
        binding.seeAllRecyclerView.layoutManager = GridLayoutManager(context, columns)
        binding.seeAllRecyclerView.adapter = adapter
        observePosterFlow(adapter)
        observeRefreshUi(adapter)
    }

    private fun observePosterFlow(adapter: PosterAdapter) {
        lifecycleScope.launchWhenStarted {
            viewModel.requestPosterData(args.compilation).collectLatest {
                adapter.submitData(it)
                viewModel.posterDataReceived()
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
        Toast.makeText(context, poster.title, Toast.LENGTH_SHORT).show() // TODO
    }
}