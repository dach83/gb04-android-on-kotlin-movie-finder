package com.example.gb04_android_on_kotlin_movie_finder.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsViewState())
    val uiState = _uiState.asLiveData()

    fun requestDetails(id: Int, contentType: ContentType) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val details = repository.requestDetails(id, contentType)
        _uiState.update { it.copy(isLoading = false, details = details) }
    }

    fun storeUserReview(userReview: String) = viewModelScope.launch {
        uiState.value?.details?.let { oldDetails ->
            if (userReview != oldDetails.userReview) {
                val details = oldDetails.copy(userReview = userReview)
                repository.storeDetails(details)
                _uiState.update { state -> state.copy(details = details) }
            }
        }
    }

    fun changeFavorites() = viewModelScope.launch {
        uiState.value?.details?.let { oldDetails ->
            val details = oldDetails.copy(favorites = !oldDetails.favorites)
            repository.storeDetails(details)
            _uiState.update { state -> state.copy(details = details) }
        }
    }

    fun changeTabPosition(tabPosition: Int) =
        _uiState.update { it.copy(tabPosition = tabPosition) }

}