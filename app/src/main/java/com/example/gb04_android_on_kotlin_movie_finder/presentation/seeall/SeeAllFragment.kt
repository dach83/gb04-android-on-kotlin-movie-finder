package com.example.gb04_android_on_kotlin_movie_finder.presentation.seeall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentSeeAllBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SeeAllFragment : Fragment(), PosterAdapter.Controller {

    private var _binding: FragmentSeeAllBinding? = null
    private val binding: FragmentSeeAllBinding get() = _binding!!

    private val viewModel: SeeAllViewModel by viewModels()

    private lateinit var compilation: Compilation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestCompilation()
        setupPosterAdapter()
        setupSwipeRefresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::requestRefresh)
    }

    private fun parseArguments() {
        compilation = requireArguments().getParcelable<Compilation>(COMPILATION_KEY) as Compilation
    }

    private fun requestCompilation() {
        viewModel.requestCompilation(compilation)
    }

    private fun setupPosterAdapter() {
        val adapter = PosterAdapter(this)
        binding.seeAllRecyclerView.adapter = adapter
        observeCompilation(adapter)
        observeRefresh(adapter)
    }

    private fun observeRefresh(adapter: PosterAdapter) {
        viewModel.refresh.observe(viewLifecycleOwner) {
            adapter.refresh()
        }
    }

    private fun observeCompilation(adapter: PosterAdapter) {
        lifecycleScope.launchWhenStarted {
            viewModel.compilation.collectLatest {
                adapter.submitData(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, poster.title, Toast.LENGTH_SHORT).show() // TODO
    }

    companion object {

        private const val COMPILATION_KEY = "compilation"

        operator fun invoke(compilation: Compilation) = SeeAllFragment().apply {
            arguments = Bundle().apply {
                putParcelable(COMPILATION_KEY, compilation)
            }
        }

    }


}