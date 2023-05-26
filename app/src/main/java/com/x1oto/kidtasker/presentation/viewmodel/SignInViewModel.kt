package com.x1oto.kidtasker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.presentation.status.Status
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _status.value = Status.Loading

            val success = authRepository.signIn(email, password)

            _status.value = if (success) {
                Status.Success("Data fetched successfully!")
            } else {
                Status.Error("Failed to fetch data!")
            }
        }
    }
}