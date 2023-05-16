package com.x1oto.kidtasker.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signUp(email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            //Log.e("Firebase", "$e")
            null
        }
    }
}
