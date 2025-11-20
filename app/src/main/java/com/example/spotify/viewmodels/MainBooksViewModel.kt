package com.example.spotify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotify.BooksByCategory
import com.example.spotify.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainBooksViewModel @Inject constructor(val repository: BooksRepository): ViewModel() {

    val categoriesList = listOf("popular", "fantasy", "children")
    private val _viewState = MutableStateFlow<ViewState>(ViewState(
        categoriesList.associateWith { ApiResult.Loading }))

    val viewState = _viewState.asStateFlow()

    fun loadBooks() {
        viewModelScope.launch(Dispatchers.IO) {
           _viewState.value.categories.keys.forEach { category ->
               val result =  async {
                    loadResult(category)
                }.await()
               _viewState.update {
                   val newCategories = it.categories.toMutableMap().apply { set(category,result) }
                   it.copy(categories = newCategories )
               }
           }


        }
    }

    init {
        loadBooks()
    }



    suspend fun loadResult(category: String): ApiResult {

        try {
            val apiResult = repository.getBooksByCategory(category)
            return ApiResult.Success(apiResult)
        }
        catch (e: Exception) {
            return ApiResult.Error(e.message ?: "error")
        }
    }
    data class ViewState(
        val categories: Map<String, ApiResult>,
    )
    sealed class ApiResult {
        object Loading : ApiResult()
        data class Success(val data: BooksByCategory?) : ApiResult()
        data class Error(val message: String) : ApiResult()
    }
}