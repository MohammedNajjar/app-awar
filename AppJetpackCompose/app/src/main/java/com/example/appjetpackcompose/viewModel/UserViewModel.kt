package com.example.appjetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import com.example.appjetpackcompose.data.network.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private val _user = MutableStateFlow(
        User(
            name = "Seraj Al Mutawa",
            location = "Riyadh, Saudi Arabia",
            favorites = 1,
            language = "English"
        )
    )
    val user: StateFlow<User> = _user.asStateFlow()
}