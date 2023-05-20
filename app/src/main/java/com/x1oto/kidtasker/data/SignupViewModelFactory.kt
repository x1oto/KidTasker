package com.x1oto.kidtasker.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.presentation.viewmodel.SignupViewModel


class SignupViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
