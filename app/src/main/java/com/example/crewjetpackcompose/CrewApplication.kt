package com.example.crewjetpackcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CrewApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
