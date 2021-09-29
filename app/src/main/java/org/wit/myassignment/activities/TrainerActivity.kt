package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.myassignment.databinding.ActivityTrainerBinding
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber
import timber.log.Timber.i

import org.wit.myassignment.R

class TrainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainerBinding
    var plan = TrainerModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Placemark Activity started...")

        binding.btnAdd.setOnClickListener() {
            plan.title = binding.placemarkTitle.text.toString()
            if (plan.title.isNotEmpty()) {
                i("add Button Pressed: $plan.title")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}