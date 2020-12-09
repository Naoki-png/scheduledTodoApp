package com.example.scheduledtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.scheduledtodo.fragments.scheduledlist.ScheduledWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WorkManager.getInstance(this)
            .enqueue(PeriodicWorkRequest.Builder(ScheduledWorker::class.java, 15, TimeUnit.MINUTES).build())

        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(bottom_nav_view, navController)
    }
}