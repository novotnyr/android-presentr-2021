package com.github.novotnyr.presentr

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val refreshTrigger = MutableLiveData<Boolean>()

    val users = refreshTrigger.switchMap {
        liveData {
            emit(presentr.getUsers())
        }
    }

    fun login(user: User) {
        viewModelScope.launch {
            presentr.login(user.login)
        }
    }

    fun refresh() {
        refreshTrigger.value = true
    }
}