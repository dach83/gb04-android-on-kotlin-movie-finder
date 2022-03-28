package com.example.gb04_android_on_kotlin_movie_finder.presentation.seeall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    private lateinit var _compilation: Flow<PagingData<Poster>>
    val compilation: Flow<PagingData<Poster>> get() = _compilation

    private val _refresh = MutableLiveData<Unit>()
    val refresh: LiveData<Unit> get() = _refresh

    fun requestCompilation(compilation: Compilation) {
        _compilation = repository.requestCompilation(compilation).cachedIn(viewModelScope)
    }

    fun requestRefresh() {
        _refresh.value = Unit
    }

}