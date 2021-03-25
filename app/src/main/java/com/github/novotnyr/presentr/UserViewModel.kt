package com.github.novotnyr.presentr

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val users = liveData {
        emit(presentr.getUsers())
    }

    fun login(user: User) {
        viewModelScope.launch {
            presentr.login(user.login)
        }
    }


}