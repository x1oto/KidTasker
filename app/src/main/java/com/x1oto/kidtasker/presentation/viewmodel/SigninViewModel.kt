package com.x1oto.kidtasker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x1oto.kidtasker.domain.AuthRepository
import kotlinx.coroutines.launch

class SigninViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> = _loginStatus

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _loginStatus.value = LoginStatus.Loading

            val success = authRepository.signIn(email, password)

            if (success) {
                _loginStatus.value = LoginStatus.Success("Logged in successfully")
            } else {
                _loginStatus.value = LoginStatus.Error("Failed to log in")
            }
        }
    }
}

sealed class LoginStatus {
    object Loading : LoginStatus()
    data class Success(val message: String) : LoginStatus()
    data class Error(val errorMessage: String) : LoginStatus()
}