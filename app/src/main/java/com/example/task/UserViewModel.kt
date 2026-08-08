package com.example.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://6a75fc7b32ae1141278417ca.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private var currentPage = 1
    private var isLastPage = false
    private val PAGE_SIZE = 20

    fun loadUsers(isRefresh: Boolean = false) {
        if (_isLoading.value == true || (isLastPage && !isRefresh)) return

        if (isRefresh) {
            currentPage = 1
            isLastPage = false
        }

        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                // MockAPI uses 'page' and 'limit' query parameters
                val response = apiService.getUsers(page = currentPage, limit = PAGE_SIZE)
                if (response.isSuccessful) {
                    val newUsers = response.body() ?: emptyList()
                    
                    if (isRefresh) {
                        _users.value = newUsers
                    } else {
                        val currentList = _users.value ?: emptyList()
                        _users.value = currentList + newUsers
                    }

                    // For MockAPI, if the number of items returned is less than the limit, 
                    // it means we've reached the end.
                    if (newUsers.size < PAGE_SIZE) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Failure: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retry() {
        loadUsers()
    }
}
