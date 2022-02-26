package com.example.gb04_android_on_kotlin_movie_finder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Poster

class CompilationsAdapter(
    private val categories: List<Category>,
    private val controller: Controller
) : RecyclerView.Adapter<CompilationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCompilationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(private val binding: ItemCompilationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var adapter: PosterAdapter = PosterAdapter(controller::onClickPoster)

        init {
            binding.posterRecyclerView.adapter = adapter
        }

        fun bind(category: Category) {
            binding.titleTextView.text = category.title
            binding.seeAllTextView.setOnClickListener { controller.onClickSeeAll(category) }
            controller.onBindPosterAdapter(category, adapter)
        }
    }

    interface Controller {
        fun onBindPosterAdapter(category: Category, posterAdapter: PosterAdapter)
        fun onClickSeeAll(category: Category)
        fun onClickPoster(poster: Poster)
    }
}