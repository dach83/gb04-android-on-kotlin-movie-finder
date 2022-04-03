package com.example.gb04_android_on_kotlin_movie_finder.presentation.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemPosterBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster

class PosterAdapter(private val controller: Controller) :
    PagingDataAdapter<Poster, PosterAdapter.ViewHolder>(POSTER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPosterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poster = getItem(position)
        if (poster != null) {
            holder.bind(poster)
        } else {
            holder.placeholder()
        }
    }

    inner class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun loadPosterImage(posterUrl: String = "") {
            if (posterUrl.isEmpty()) {
                binding.posterImageView.setImageResource(R.drawable.ic_poster_placeholder)
            } else {
                binding.posterImageView.load(posterUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_poster_placeholder)
                }
            }
        }

        fun bind(poster: Poster) = binding.apply {
            loadPosterImage(poster.posterImage.imageUrl())
            root.setOnClickListener { controller.onClickPoster(poster) }
        }

        fun placeholder() = binding.apply {
            loadPosterImage()
            root.setOnClickListener(null)
        }
    }

    interface Controller {
        fun onClickPoster(poster: Poster)
    }
}

private val POSTER_COMPARATOR = object : DiffUtil.ItemCallback<Poster>() {

    override fun areItemsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return (oldItem.contentId == newItem.contentId) && (oldItem.contentType == newItem.contentType)
    }

    override fun areContentsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem == newItem
    }
}
