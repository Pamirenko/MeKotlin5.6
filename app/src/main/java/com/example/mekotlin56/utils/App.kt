package com.example.mekotlin56.utils

import android.app.Application
import androidx.room.Room
import com.example.mekotlin56.data.local.AppDatabase

class App : Application() {

    companion object {

        lateinit var db: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(applicationContext)
    }
}