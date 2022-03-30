package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gb04_android_on_kotlin_movie_finder.data.Repository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CompilationViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _compilations: MutableMap<Compilation, Flow<PagingData<Poster>>> = mutableMapOf()
    val compilations: Map<Compilation, Flow<PagingData<Poster>>> get() = _compilations

    private val _refresh = MutableLiveData<Unit>()
    val refresh: LiveData<Unit> get() = _refresh

    fun requestCompilation(compilation: Compilation) {
        _compilations[compilation] =
            repository.requestCompilation(compilation).cachedIn(viewModelScope)
    }

    fun refreshCompilations() {
        _refresh.value = Unit
    }

}

