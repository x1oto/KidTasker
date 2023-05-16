package com.x1oto.kidtasker.domain

interface SignupRepository {
    suspend fun signUp(email: String, password: String): Boolean
}