package com.x1oto.kidtasker.data

import com.x1oto.kidtasker.data.model.User
import com.x1oto.kidtasker.domain.AuthRepository

class AuthRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : AuthRepository {

    override suspend fun signUp(name: String, email: String, password: String, role: String): Boolean {
        return firebaseDataSource.signUp(email, password)?.let {
            firebaseDataSource.uploadDataToDatabase(name, email, role)
        } ?: false
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        return firebaseDataSource.signIn(email, password) != null
    }

    override suspend fun fetchDataFromFirebase(): User? {
        return firebaseDataSource.fetchDataFromFirebase()
    }

    override suspend fun signOut(): Boolean {
        return firebaseDataSource.signOut()
    }

}