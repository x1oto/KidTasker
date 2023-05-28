package com.x1oto.kidtasker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.presentation.status.Status
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    fun signUp(name: String, email: String, password: String, parent: Boolean) {
        viewModelScope.launch {
            _status.value = Status.Loading

            val success = authRepository.signUp(name, email, password, parent)

            _status.value = if (success) {
                Status.Success("Registered successfully")
            } else {
                Status.Error("Failed to sign up")
            }
        }
    }
}
