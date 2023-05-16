package com.x1oto.kidtasker.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.x1oto.kidtasker.domain.SignupRepository
import com.x1oto.kidtasker.presentation.viewmodel.SignupViewModel


class SignupViewModelFactory(private val signupRepository: SignupRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(signupRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
