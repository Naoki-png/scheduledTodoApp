package com.example.scheduledtodo.fragments.scheduledlist

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.scheduledtodo.MainActivity
import com.example.scheduledtodo.R
import com.example.scheduledtodo.Utils.REQUEST_PENDING_INTENT
import com.example.scheduledtodo.Utils.SEND_NOTIFICATION
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
        sendNotification(context)

        return Result.success()
    }

    private fun sendNotification(context: Context) {
        val pendingIntent = PendingIntent.getActivity(
                context,
                REQUEST_PENDING_INTENT,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(context, "channel_1")
                .setSmallIcon(R.drawable.ic_baseline_schedule_24)
                .setContentTitle("scheduled Task")
                .setContentText("scheduled Task is added to today's todo list.")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

        val notification = notificationBuilder.build()
        notification.flags = Notification.DEFAULT_LIGHTS or Notification.FLAG_AUTO_CANCEL
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(SEND_NOTIFICATION, notification)
    }
}