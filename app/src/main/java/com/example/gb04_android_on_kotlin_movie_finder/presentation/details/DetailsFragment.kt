package com.example.gb04_android_on_kotlin_movie_finder.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentDetailsBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.ImageSize
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding as FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestDetails()
        observeDetails()
        setupToolbar()
        setupTabLayout()
        setupFavorites()
    }

    override fun onStop() {
        super.onStop()
        storeUserReview()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        val navController = binding.root.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.collapsingLayout.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
    }

    private fun setupFavorites() =
        binding.favoritesImageView.setOnClickListener { viewModel.changeFavorites() }

    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) = when (tab.position) {
                0 -> binding.overviewTextView.isVisible = true
                1 -> binding.userReviewEditText.isVisible = true
                else -> throw IllegalArgumentException("Unknown Tab")
            }.also {
                viewModel.changeTabPosition(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) = when (tab.position) {
                0 -> binding.overviewTextView.isVisible = false
                1 -> binding.userReviewEditText.isVisible = false
                else -> throw IllegalArgumentException("Unknown Tab")
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun requestDetails() {
        viewModel.requestDetails(args.contentId, args.contentType)
    }

    private fun observeDetails() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.apply {
                loadingProgressBar.isVisible = uiState.isLoading
                tabLayout.selectTab(tabLayout.getTabAt(uiState.tabPosition))
                uiState.details?.let { details ->
                    collapsingLayout.title = details.title
                    titleTextView.text = details.title
                    taglineTextView.text = details.tagline
                    overviewTextView.text = details.overview
                    userReviewEditText.setText(details.userReview)
                    val colorId = when (details.favorites) {
                        true -> R.color.in_favorites_color
                        false -> R.color.no_favorites_color
                    }
                    context?.let { favoritesImageView.setColorFilter(it.getColor(colorId)) }
                    posterLayout.posterImageView.load(details.posterImage.imageUrl()) {
                        crossfade(true)
                        placeholder(R.drawable.ic_poster_placeholder)
                    }
                    backdropImageView.load(details.backdropImage.imageUrl(ImageSize.LARGE)) {
                        crossfade(true)
                    }
                }
            }
        }
    }

    private fun storeUserReview() {
        viewModel.storeUserReview(binding.userReviewEditText.text.toString())
    }

}