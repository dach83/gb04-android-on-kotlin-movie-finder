package com.example.gb04_android_on_kotlin_movie_finder.presentation.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemPosterBinding
import com.example.gb04_android_on_kotlin_movie_finder.presentation.poster.PosterLoadStateAdapter.Holder

typealias TryAgainAction = () -> Unit

class PosterLoadStateAdapter(private val tryAgainAction: TryAgainAction): LoadStateAdapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPosterBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class Holder(binding: ItemPosterBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            //TODO("Not yet implemented")
        }
    }
}