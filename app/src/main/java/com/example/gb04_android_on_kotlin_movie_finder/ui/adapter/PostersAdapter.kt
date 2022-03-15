package com.example.gb04_android_on_kotlin_movie_finder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemPosterBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster

class PosterAdapter(private val onClickPoster: (poster: Poster) -> Unit) :
    ListAdapter<Poster, PosterAdapter.ViewHolder>(PosterDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(poster: Poster) {
            binding.titleTextView.text = poster.title
            binding.posterImageView.setOnClickListener { onClickPoster(poster) }
        }
    }
}

private class PosterDiff : DiffUtil.ItemCallback<Poster>() {
    override fun areItemsTheSame(oldItem: Poster, newItem: Poster) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Poster, newItem: Poster) = oldItem == newItem
}
