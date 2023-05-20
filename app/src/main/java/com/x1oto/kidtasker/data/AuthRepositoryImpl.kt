package com.x1oto.kidtasker.data

import com.x1oto.kidtasker.domain.AuthRepository

class AuthRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : AuthRepository {

    override suspend fun signUp(name: String, email: String, password: String, accountCategory: String): Boolean {
        val user = firebaseDataSource.signUp(email, password)
        if(user != null){
            return firebaseDataSource.uploadDataToDatabase(name, email, accountCategory)
        }
        return false
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        val user = firebaseDataSource.signIn(email, password)
        return user != null
    }
}