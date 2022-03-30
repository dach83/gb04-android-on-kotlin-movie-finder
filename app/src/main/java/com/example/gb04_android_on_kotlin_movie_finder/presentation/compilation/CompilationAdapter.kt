package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb04_android_on_kotlin_movie_finder.databinding.ItemCompilationBinding
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation

class CompilationAdapter(
    private val compilations: List<Compilation>,
    private val controller: Controller
) :
    RecyclerView.Adapter<CompilationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCompilationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(compilations[position])
    }

    override fun getItemCount(): Int {
        return compilations.size
    }

    inner class ViewHolder(private val binding: ItemCompilationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(compilation: Compilation) {
            binding.apply {
                titleTextView.setText(compilation.title)
                controller.setupPosterAdapter(compilation, posterRecyclerView)
                seeAllTextView.setOnClickListener { controller.onClickSeeAll(compilation) }
            }
        }
    }

    interface Controller {
        fun setupPosterAdapter(compilation: Compilation, recyclerView: RecyclerView)
        fun onClickSeeAll(compilation: Compilation)
    }

}