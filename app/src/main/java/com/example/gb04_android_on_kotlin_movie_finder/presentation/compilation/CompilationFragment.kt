package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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

    private lateinit var compilations: List<Compilation>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompilationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCompilations()
        setupCompilationAdapter()
        setupSwipeRefresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::requestRefresh)
    }

    private fun setupCompilations() {
        compilations = when (args.contentType) {
            ContentType.MOVIE -> movieCompilations
            ContentType.TVSHOW -> tvShowCompilations
        }
        compilations.forEach(viewModel::requestCompilation)
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
        observeCompilation(compilation, adapter)
        observeRefresh(adapter)
    }

    private fun observeCompilation(
        compilation: Compilation,
        posterAdapter: PosterAdapter
    ) {
        lifecycleScope.launchWhenStarted {
            viewModel.compilations[compilation]?.collectLatest {
                posterAdapter.submitData(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun observeRefresh(adapter: PosterAdapter) {
        viewModel.refresh.observe(viewLifecycleOwner) {
            adapter.refresh()
        }
    }

    override fun onClickSeeAll(compilation: Compilation) {
        val action = CompilationFragmentDirections.actionCompilationFragmentToSeeAllActivity(compilation)
        findNavController().navigate(action)
    }

    override fun onClickPoster(poster: Poster) {
        Toast.makeText(context, poster.title, Toast.LENGTH_SHORT).show() // TODO
    }

}