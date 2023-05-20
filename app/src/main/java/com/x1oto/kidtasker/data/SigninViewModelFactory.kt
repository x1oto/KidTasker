package com.x1oto.kidtasker.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.presentation.viewmodel.SigninViewModel


class SigninViewModelFactory(private val authRepository: AuthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SigninViewModel::class.java)) {
            return SigninViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}