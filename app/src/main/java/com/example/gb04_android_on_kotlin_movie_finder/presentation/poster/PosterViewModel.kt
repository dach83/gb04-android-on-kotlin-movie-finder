package com.example.gb04_android_on_kotlin_movie_finder.presentation.poster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PosterViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    private var posterPagingFlow: Flow<PagingData<Poster>>? = null

    private val _uiState = MutableStateFlow(PosterViewState())
    val uiState = _uiState.asLiveData()

    fun requestPosters(compilation: Compilation): Flow<PagingData<Poster>> {
        if (posterPagingFlow == null) {
            posterPagingFlow = repository.requestCompilation(compilation).cachedIn(viewModelScope)
        }
        return posterPagingFlow as Flow<PagingData<Poster>>
    }

    fun refreshUi() = _uiState.update { currentState ->
        currentState.copy(isRefreshing = true)
    }

    fun uiRefreshed() = _uiState.update { currentState ->
        currentState.copy(isRefreshing = false)
    }
}