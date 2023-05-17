package com.x1oto.kidtasker.data

import com.x1oto.kidtasker.domain.SignupRepository

class SignupRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : SignupRepository {

    override suspend fun signUp(email: String, password: String): Boolean {
        val user = firebaseDataSource.signUp(email, password)
        return user != null
    }

}