package com.example.scheduledtodo.fragments.scheduledlist

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.scheduledtodo.data.Repository
import com.example.scheduledtodo.data.TodoDatabase
import com.example.scheduledtodo.data.TodoModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduledWorker(val context: Context, param: WorkerParameters): Worker(context, param) {
    val repository = Repository(TodoDatabase.getDatabase(context).getTodoDao())
    override fun doWork(): Result {
        val scheduledData = TodoModel(0, "scheduled", "scheduled", "Sunday")
        GlobalScope.launch {
            repository.insertData(scheduledData)
        }
        //notification

        return Result.success()
    }
}