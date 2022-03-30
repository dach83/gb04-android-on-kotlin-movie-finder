package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gb04_android_on_kotlin_movie_finder.data.Repository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CompilationViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val compilationFlow: MutableMap<Compilation, Flow<PagingData<Poster>>> = mutableMapOf()

    private val _uiState = MutableStateFlow(CompilationViewState())
    val uiState = _uiState.asLiveData()

    fun requestCompilationFlow(compilation: Compilation): Flow<PagingData<Poster>> =
        compilationFlow.computeIfAbsent(compilation) {
            repository.requestCompilation(it).cachedIn(viewModelScope)
        }

    fun compilationFlowReceived() = _uiState.update { currentState ->
        currentState.copy(isInitialLoading = false, isRefreshing = false)
    }

    fun refreshUi() = _uiState.update { currentState ->
        currentState.copy(isRefreshing = true)
    }

}

