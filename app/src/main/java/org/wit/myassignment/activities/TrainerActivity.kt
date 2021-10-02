package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityTrainerBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber.i

class TrainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainerBinding
    var plan = TrainerModel()
    lateinit var app : MainApp

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_plan, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Application Starting...")


        binding.btnAdd.setOnClickListener() {
            plan.title = binding.planTitle.text.toString()
            if (plan.title.isNotEmpty()) {
                app.plans.add(plan.copy())
                i("add Button Pressed: ${plan}")
                for (i in app.plans.indices) {
                    i("Placemark[$i]:${this.app.plans[i]}")
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}