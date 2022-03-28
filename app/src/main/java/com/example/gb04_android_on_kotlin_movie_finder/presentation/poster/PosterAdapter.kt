package com.example.gb04_android_on_kotlin_movie_finder.presentation.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemPosterBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.POSTER_BASE_URL
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
        holder.bind(poster)
    }

    inner class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(poster: Poster?) {
            val posterPath = poster?.posterPath.orEmpty()
            binding.apply {
                if (posterPath.isEmpty()) {
                    posterImageView.setImageResource(R.drawable.ic_movie)
                } else {
                    posterImageView.load(POSTER_BASE_URL + posterPath) {
                        crossfade(true)
                        placeholder(R.drawable.ic_movie)
                    }
                }

                if (poster != null) {
                    root.setOnClickListener { controller.onClickPoster(poster) }
                }
            }
        }
    }

    interface Controller {
        fun onClickPoster(poster: Poster)
    }
}

class PosterDiffCallback : DiffUtil.ItemCallback<Poster>() {

    override fun areItemsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem.posterPath == newItem.posterPath
    }

    override fun areContentsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem == newItem
    }
}
