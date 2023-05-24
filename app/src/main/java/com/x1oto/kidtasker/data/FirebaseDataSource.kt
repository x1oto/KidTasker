package com.x1oto.kidtasker.data

import android.nfc.Tag
import android.util.Log
import androidx.fragment.app.FragmentManager.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.x1oto.kidtasker.data.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseDataSource {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userRef = FirebaseDatabase.getInstance("bruh")

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUp(email: String, password: String): FirebaseUser? {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                continuation.resume(it.user){}
            }
            .addOnFailureListener {
                continuation.resume(null){}
            }
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun uploadDataToDatabase(name: String, email: String, role: String): Boolean {
        return suspendCancellableCoroutine { continuation ->
            userRef.reference.child("General/Users/${firebaseAuth.currentUser!!.uid}").setValue(User(name, email, role, balance = 0))
            .addOnSuccessListener {
                continuation.resume(true){}
            }
            .addOnFailureListener {
                continuation.resume(false){}
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signIn(email: String, password: String): FirebaseUser? {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                continuation.resume(it.user){}
            }
            .addOnFailureListener {
                continuation.resume(null){}
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun fetchDataFromFirebase(): User? {
        return suspendCancellableCoroutine { continuation ->
            val databaseRef = FirebaseDatabase.getInstance("bruh").getReference("General/Users/${firebaseAuth.currentUser!!.uid}")

            databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    continuation.resume(user){}
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(null){}
                }
            })
        }
    }


    suspend fun signOut(): Boolean {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signOut()
            continuation.resumeWith(Result.success(true))
        }
    }
}
