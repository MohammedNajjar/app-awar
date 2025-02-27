package com.example.appjetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appjetpackcompose.data.network.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val repository = OrderRepository.instance

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPost: StateFlow<Post?> = _selectedPost.asStateFlow()

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = repository.getOrders()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun fetchPostDetails(id: Int) {
        viewModelScope.launch {
            try {
                _selectedPost.value = repository.getOrderById(id)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}