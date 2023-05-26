package com.x1oto.kidtasker.data

import com.x1oto.kidtasker.data.model.User
import com.x1oto.kidtasker.domain.TaskRepository

class TaskRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource
): TaskRepository {

    override suspend fun fetchDataFromFirebase(): User? {
        return firebaseDataSource.fetchDataFromFirebase()
    }

    override suspend fun signOut(): Boolean {
        return firebaseDataSource.signOut()
    }
}