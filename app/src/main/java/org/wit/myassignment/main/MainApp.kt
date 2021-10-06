package org.wit.myassignment.main

import android.app.Application
import org.wit.myassignment.models.PlanMemStore
import org.wit.myassignment.models.RoutineMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val plans = PlanMemStore()
    val routines = RoutineMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Gym Trainer started")
    }
}