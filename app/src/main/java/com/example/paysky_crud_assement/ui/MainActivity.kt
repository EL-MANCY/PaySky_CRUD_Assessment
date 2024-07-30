package com.example.paysky_crud_assement.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.paysky_crud_assement.data.local.DataSyncWorker
import com.example.paysky_crud_assement.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scheduleDataSyncWorker()
    }

    private fun scheduleDataSyncWorker() {
        val syncWorkRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(
            5, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DataSyncWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }
}
