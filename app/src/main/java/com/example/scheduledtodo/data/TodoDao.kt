package com.example.scheduledtodo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(todoModel: TodoModel)

    @Update
    suspend fun updateData(todoModel: TodoModel)

    @Delete
    suspend fun deleteData(todoModel: TodoModel)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllData()

    @Query("SELECT * FROM todo_table WHERE scheduledTime = 'Sunday'")
    fun getSundayData(): LiveData<List<TodoModel>>
}