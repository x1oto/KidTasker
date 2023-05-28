package com.x1oto.kidtasker.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x1oto.kidtasker.data.model.Task
import com.x1oto.kidtasker.data.model.User
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.domain.TaskRepository
import com.x1oto.kidtasker.presentation.status.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _dataStatus = MutableLiveData<Status>()
    val dataStatus: LiveData<Status> = _dataStatus

    private val _taskStatus = MutableLiveData<Status>()
    val taskStatus: LiveData<Status> = _taskStatus

    private val _userRole = MutableLiveData<Boolean>()
    val userRole: LiveData<Boolean> = _userRole

    private val _taskList = MutableLiveData<List<Task>?>()
    val taskList: LiveData<List<Task>?> = _taskList

    fun fetchData() {
        viewModelScope.launch {
            _dataStatus.value = Status.Loading

            val user = taskRepository.fetchDataFromFirebase()

            if (user != null) {
                setUserRole(user)
                _dataStatus.value = Status.Success("Data fetched successfully!")
            } else {
                _dataStatus.value = Status.Error("Failed to fetch data!")
            }
        }
    }

    private fun setUserRole(user: User) {
        _userRole.value = user.parent ?: false
    }

    fun fetchTasks() {
        viewModelScope.launch {
            _taskStatus.value = Status.Loading

            val tasks = taskRepository.fetchTasksFromFirebase()

            if (tasks != null) {
                _taskList.value = tasks
                _taskStatus.value = Status.Success("List fetched successfully!")
            } else {
                _taskStatus.value = Status.Error("Failed to fetch list!")
            }
        }
    }

    /*fun signOut() {
        viewModelScope.launch {
            taskRepository.signOut()
        }
    }*/
}
