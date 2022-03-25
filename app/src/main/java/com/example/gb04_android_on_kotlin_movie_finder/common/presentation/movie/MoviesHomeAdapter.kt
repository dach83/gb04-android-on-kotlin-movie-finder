package com.example.gb04_android_on_kotlin_movie_finder.common.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding

class MoviesHomeAdapter: RecyclerView.Adapter<MoviesHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCompilationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(MovieCompilation.values()[position])
    }

    override fun getItemCount(): Int = MovieCompilation.values().size

    class ViewHolder(private val binding: ItemCompilationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieCompilation: MovieCompilation) {
            binding.apply {
                titleTextView.text = movieCompilation.title
            }
        }
    }

}