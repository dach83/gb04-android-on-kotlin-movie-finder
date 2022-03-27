package com.example.gb04_android_on_kotlin_movie_finder.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.movie.MovieCompilation

class MovieCompilationAdapter(private val controller: Controller) :
    RecyclerView.Adapter<MovieCompilationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCompilationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(MovieCompilation.values()[position])
    }

    override fun getItemCount(): Int {
        return MovieCompilation.values().size
    }

    inner class ViewHolder(private val binding: ItemCompilationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(compilation: MovieCompilation) {
            binding.apply {
                titleTextView.setText(compilation.title)
                controller.setupPosterAdapter(compilation, posterRecyclerView)
                seeAllTextView.setOnClickListener { controller.onClickSeeAll(compilation) }
            }
        }
    }

    interface Controller {
        fun setupPosterAdapter(compilation: MovieCompilation, recyclerView: RecyclerView)
        fun onClickSeeAll(compilation: MovieCompilation)
    }

}