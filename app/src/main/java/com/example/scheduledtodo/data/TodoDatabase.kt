package com.example.scheduledtodo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 1, exportSchema = true)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object {
        //meaning that writes to this field are immediately made visible to other threads.
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        /**
         * databaseのインスタンスをsingletonの状態で返すメソッド
         */
        fun getDatabase(context: Context): TodoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //singletonにしたい
            //ここのlockインスタンスのthisはcompanion objectのこと。Companionというクラスのシングルトンインスタンスらしい
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}