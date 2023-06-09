package com.x1oto.kidtasker.domain

import com.x1oto.kidtasker.data.model.User

interface AuthRepository {
    suspend fun signUp(
        name: String, email: String, password: String, parent: Boolean
    ): Boolean
    suspend fun signIn(email: String, password: String): Boolean
}
