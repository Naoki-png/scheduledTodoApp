package com.example.scheduledtodo.data

import androidx.lifecycle.LiveData

class Repository(private val todoDao: TodoDao) {
    val getAllData: LiveData<List<TodoModel>> = todoDao.getAllData()
    val getSundayData: LiveData<List<TodoModel>> = todoDao.getSundayData()

    suspend fun insertData(todoModel: TodoModel) {
        return todoDao.insertData(todoModel)
    }

    suspend fun updateData(todoModel: TodoModel) {
        return todoDao.updateData(todoModel)
    }

    suspend fun deleteData(todoModel: TodoModel) {
        return todoDao.deleteData(todoModel)
    }

    suspend fun deleteAllData() {
        return todoDao.deleteAllData()
    }

}