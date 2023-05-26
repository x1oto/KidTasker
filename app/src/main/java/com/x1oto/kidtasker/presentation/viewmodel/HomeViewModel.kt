package com.x1oto.kidtasker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x1oto.kidtasker.data.model.User
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.domain.TaskRepository
import com.x1oto.kidtasker.presentation.status.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: TaskRepository
): ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    fun fetchDataFromFirebase() {
        viewModelScope.launch {
            _status.value = Status.Loading

            val user = authRepository.fetchDataFromFirebase()

            _status.value = if (user != null) {
                _userData.value = user
                Status.Success("Data fetched successfully!")
            } else {
                Status.Error("Failed to fetch data!")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }
}
