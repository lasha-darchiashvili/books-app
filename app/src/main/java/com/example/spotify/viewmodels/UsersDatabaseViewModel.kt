package com.example.spotify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotify.entities.NewUser
import com.example.spotify.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UsersUiState(
    val allUsers: List<NewUser> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class UsersDatabaseViewModel @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<UsersUiState>(UsersUiState(isLoading = true))

    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collect{ users ->
                _viewState.update {
                    it.copy(allUsers = users )
                }
            }

        }
    }

    fun insertIntoDb(userName: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(userName, email, password)
        }
    }
    fun getUser(userName: String) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getUser(userName)
            }
        }

}
