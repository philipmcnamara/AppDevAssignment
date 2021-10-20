package org.wit.myassignment.main

import android.app.Application
import org.wit.myassignment.models.PlanJSONStore
import org.wit.myassignment.models.PlanMemStore
import org.wit.myassignment.models.PlanStore
import org.wit.myassignment.models.RoutineMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val routines = RoutineMemStore()
    lateinit var plans: PlanStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        plans = PlanJSONStore(applicationContext)
        //plans = PlanMemStore()
        i("Gym Trainer started")
    }
}