package org.wit.myassignment.main

import android.app.Application
import org.wit.myassignment.models.PlanMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val plans = PlanMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Gym Trainer started")
    }
}