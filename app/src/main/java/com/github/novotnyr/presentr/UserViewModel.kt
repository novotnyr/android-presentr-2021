package com.github.novotnyr.presentr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    fun login(user: User) {
        viewModelScope.launch {
            presentr.login(user.login)
        }
    }
}