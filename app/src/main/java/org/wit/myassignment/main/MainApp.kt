package org.wit.myassignment.main

import android.app.Application
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val plans = ArrayList<TrainerModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")
        /*plans.add(TrainerModel("One"))
        plans.add(TrainerModel("Two"))
        plans.add(TrainerModel("Three"))*/
    }
}