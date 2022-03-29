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
    PagingDataAdapter<Poster, PosterAdapter.ViewHolder>(PosterDiffCallback()) {

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

        fun bind(poster: Poster) {
            binding.posterImageView.load(poster.posterUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_movie)
            }
        }

        fun placeholder() {
            binding.posterImageView.setImageResource(R.drawable.ic_movie)
        }
    }

    interface Controller {
        fun onClickPoster(poster: Poster)
    }
}

class PosterDiffCallback : DiffUtil.ItemCallback<Poster>() {

    override fun areItemsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem.posterUrl == newItem.posterUrl
    }

    override fun areContentsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem == newItem
    }
}
