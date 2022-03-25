package com.example.gb04_android_on_kotlin_movie_finder.common.presentation.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemPosterBinding

class PosterCompilationAdapter : ListAdapter<UiPoster, PosterCompilationAdapter.ViewHolder>(
    ITEM_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(poster: UiPoster) {
            binding.titleTextView.text = poster.title
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UiPoster>() {
    override fun areItemsTheSame(oldItem: UiPoster, newItem: UiPoster) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: UiPoster, newItem: UiPoster) = oldItem == newItem
}
