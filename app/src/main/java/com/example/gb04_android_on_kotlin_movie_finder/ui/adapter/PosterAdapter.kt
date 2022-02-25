package com.example.gb04_android_on_kotlin_movie_finder.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster

class PosterAdapter : ListAdapter<Poster, PosterAdapter.ViewHolder>(PosterDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(poster: Poster) {
            // TODO
        }
    }
}

private class PosterDiff : DiffUtil.ItemCallback<Poster>() {
    override fun areItemsTheSame(oldItem: Poster, newItem: Poster) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Poster, newItem: Poster) = oldItem == newItem
}
