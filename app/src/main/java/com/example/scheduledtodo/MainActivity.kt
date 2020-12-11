package com.example.scheduledtodo

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.scheduledtodo.Utils.REQUEST_PENDING_INTENT
import com.example.scheduledtodo.Utils.SCHEDULED_WORK
import com.example.scheduledtodo.Utils.SEND_NOTIFICATION
import com.example.scheduledtodo.fragments.scheduledlist.ScheduledWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //WorkManager.getInstance(this)はsingletonを返すのでインスタンス生成チェック不要
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                    SCHEDULED_WORK,
                    ExistingPeriodicWorkPolicy.KEEP,
                    PeriodicWorkRequest.Builder(ScheduledWorker::class.java, 15, TimeUnit.MINUTES).build())

        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(bottom_nav_view, navController)
    }
}