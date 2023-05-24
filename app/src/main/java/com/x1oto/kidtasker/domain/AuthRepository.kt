package com.x1oto.kidtasker.domain

import com.x1oto.kidtasker.data.model.User

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String, role: String): Boolean
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun fetchDataFromFirebase(): User?
    suspend fun signOut(): Boolean
}
