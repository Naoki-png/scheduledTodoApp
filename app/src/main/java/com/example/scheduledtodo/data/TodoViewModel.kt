package com.example.scheduledtodo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDatabase(application).getTodoDao()
    private val repository = Repository(todoDao)

    val getAllData = repository.getAllData

    fun insertData(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(todoModel)
        }
    }

    fun updateData(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(todoModel)
        }
    }

    fun deleteData(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(todoModel)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

}