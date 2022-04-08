package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import com.example.gb04_android_on_kotlin_movie_finder.presentation.applySettings
import com.example.gb04_android_on_kotlin_movie_finder.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CompilationViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    private val compilationFlow: MutableMap<Compilation, Flow<PagingData<Poster>>> = mutableMapOf()

    private val _uiState = MutableStateFlow(CompilationViewState())
    val uiState = _uiState.asLiveData()

    fun requestCompilationData(
        compilation: Compilation,
    ): Flow<PagingData<Poster>> {
        _uiState.update { it.copy(isLoading = true) }
        if (compilationFlow[compilation] == null) {
            compilationFlow[compilation] =
                repository.requestCompilation(compilation).cachedIn(viewModelScope)
        }
        return (compilationFlow[compilation] as Flow<PagingData<Poster>>)
            .applySettings(repository.loadSettings())
    }

    fun compilationDataReceived() = _uiState.update { currentState ->
        currentState.copy(isLoading = false, isRefreshing = false)
    }

    fun refreshUi() = _uiState.update { currentState ->
        currentState.copy(isRefreshing = true)
    }

    fun onFailure(failure: Throwable) = _uiState.update { currentState ->
        currentState.copy(failure = Event(failure))
    }

}

