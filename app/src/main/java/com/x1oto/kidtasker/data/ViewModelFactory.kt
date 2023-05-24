package com.x1oto.kidtasker.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.presentation.viewmodel.HomeViewModel
import com.x1oto.kidtasker.presentation.viewmodel.SignInViewModel
import com.x1oto.kidtasker.presentation.viewmodel.SignUpViewModel

class ViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(repository) as T
            SignInViewModel::class.java -> SignInViewModel(repository) as T
            SignUpViewModel::class.java -> SignUpViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}