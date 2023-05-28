package com.x1oto.kidtasker.domain

import com.x1oto.kidtasker.data.model.Task
import com.x1oto.kidtasker.data.model.User

interface TaskRepository {
    suspend fun fetchDataFromFirebase(): User?
    suspend fun fetchTasksFromFirebase(): List<Task>?
    suspend fun signOut(): Boolean
}