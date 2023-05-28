package com.x1oto.kidtasker.data

import com.x1oto.kidtasker.data.model.User
import com.x1oto.kidtasker.domain.AuthRepository

class AuthRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource
): AuthRepository {
    override suspend fun signUp(name: String, email: String, password: String, parent: Boolean): Boolean {
        return firebaseDataSource.signUp(email, password)?.let {
            firebaseDataSource.uploadDataToDatabase(name, email, parent)
        } ?: false
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        return firebaseDataSource.signIn(email, password) != null
    }
}