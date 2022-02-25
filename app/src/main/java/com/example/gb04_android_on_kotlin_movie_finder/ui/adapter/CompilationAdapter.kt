package com.example.gb04_android_on_kotlin_movie_finder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category

class CompilationAdapter(
    private val categories: List<Category>,
    val onChangePosterAdapter: (category: Category, adapter: PosterAdapter) -> Unit
) : RecyclerView.Adapter<CompilationAdapter.ViewHolder>() {

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

        private var adapter: PosterAdapter = PosterAdapter()

        init {
            binding.posterRecyclerView.adapter = adapter
        }

        fun bind(category: Category) {
            binding.titleTextView.text = category.title
            onChangePosterAdapter(category, adapter)
        }
    }
}