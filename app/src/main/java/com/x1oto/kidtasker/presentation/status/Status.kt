package com.x1oto.kidtasker.presentation.status

sealed class Status {
    object Loading : Status()
    data class Success(val message: String) : Status()
    data class Error(val errorMessage: String) : Status()
}
