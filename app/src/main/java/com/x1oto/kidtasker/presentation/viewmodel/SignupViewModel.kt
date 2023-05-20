package com.x1oto.kidtasker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x1oto.kidtasker.domain.AuthRepository
import kotlinx.coroutines.launch


class SignupViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _signupStatus = MutableLiveData<SignupStatus>()
    val signupStatus: LiveData<SignupStatus> = _signupStatus

    fun signUp(name: String, email: String, password: String, accountCategory: String) {
        viewModelScope.launch {
            _signupStatus.value = SignupStatus.Loading

            val success = authRepository.signUp(name, email, password, accountCategory)

            if (success) {
                _signupStatus.value = SignupStatus.Success("Registered successfully")
            } else {
                _signupStatus.value = SignupStatus.Error("Failed to sign up")
            }
        }
    }
}

sealed class SignupStatus {
    object Loading : SignupStatus()
    data class Success(val message: String) : SignupStatus()
    data class Error(val errorMessage: String) : SignupStatus()
}
