package org.wit.myassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.myassignment.databinding.ActivityTrainerBinding
import timber.log.Timber
import timber.log.Timber.i



class TrainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_trainer)

        Timber.plant(Timber.DebugTree())

        i("Trainer Activity started..")

        binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }
    }
}