package com.x1oto.kidtasker.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.x1oto.kidtasker.data.model.User
import kotlinx.coroutines.tasks.await

class FirebaseDataSource {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    //private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    //private val usersRef: DatabaseReference = database.reference.child("users")

    suspend fun signUp(email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

}
