package com.x1oto.kidtasker.domain

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String, accountCategory: String): Boolean
    suspend fun signIn(email: String, password: String): Boolean
}
