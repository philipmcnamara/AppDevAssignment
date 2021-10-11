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
import timber.log.Timber.i

class RoutineActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityPlansBinding
    var routine = exerciseModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var routineEdit = false

        binding = ActivityPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAddRoutine.title = title
        setSupportActionBar(binding.toolbarAddRoutine)

        app = application as MainApp

        if (intent.hasExtra("routine_Edit")) {
            routineEdit = true
            routine = intent.extras?.getParcelable("routine_Edit")!!
            binding.planRoutine.setText(routine.title)
            binding.planSet1.setText(routine.Set1)
            binding.btnAdd.setText(R.string.save_routine)
        }

        binding.btnAdd.setOnClickListener() {
            routine.title = binding.planRoutine.text.toString()
            routine.Set1 = binding.planSet1.text.toString()
            if (routine.title.isEmpty()) {
                i("add Button Pressed: ${routine}")
                Snackbar.make(it,R.string.enter_routine_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (routineEdit) {
                    app.routines.update(routine.copy())
                } else {
                    app.routines.create(routine.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
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

