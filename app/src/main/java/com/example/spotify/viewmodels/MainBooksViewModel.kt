package com.example.spotify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotify.BooksByCategory
import com.example.spotify.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainBooksViewModel @Inject constructor(val repository: BooksRepository): ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    val viewState = _viewState.asStateFlow()

    fun loadBooks(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResult = repository.getBooksByCategory(category)
                _viewState.value = com.example.spotify.viewmodels.MainBooksViewModel.ViewState.Success(apiResult)
            }
            catch (e: Exception) {
                _viewState.value =  com.example.spotify.viewmodels.MainBooksViewModel.ViewState.Error("error")
            }
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val data: BooksByCategory?) : ViewState()
        data class Error(val message: String) : ViewState()
    }
}