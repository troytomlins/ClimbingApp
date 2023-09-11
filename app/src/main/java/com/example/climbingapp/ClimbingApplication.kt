package com.example.climbingapp

import android.app.Application
import com.example.climbingapp.data.AppContainer
import com.example.climbingapp.data.AppDataContainer

class ClimbingApplication: Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}