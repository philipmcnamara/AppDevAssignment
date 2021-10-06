package org.wit.myassignment.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityPlansBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.exerciseModel

class RoutineActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityPlansBinding
    var routine = exerciseModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("routine_Edit")) {
            routine = intent.extras?.getParcelable("routine_Edit")!!
            binding.planRoutine.setText(routine.title)
        }

        binding.btnAdd.setOnClickListener() {
            routine.title = binding.planRoutine.text.toString()
            if (routine.title.isNotEmpty()) {
                app.routines.create(routine.copy())
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

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
}

