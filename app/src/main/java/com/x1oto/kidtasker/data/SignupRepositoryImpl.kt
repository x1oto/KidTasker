package com.x1oto.kidtasker.data

import com.x1oto.kidtasker.domain.SignupRepository

class SignupRepositoryImpl(private val firebaseAuthDataSource: FirebaseAuthDataSource) : SignupRepository {

    override suspend fun signUp(email: String, password: String): Boolean {
        val user = firebaseAuthDataSource.signUp(email, password)
        return user != null
    }

}