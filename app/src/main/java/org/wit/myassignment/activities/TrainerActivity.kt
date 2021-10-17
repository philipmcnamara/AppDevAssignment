package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.google.android.material.snackbar.Snackbar
import org.wit.myassignment.R
import org.wit.myassignment.R.id.item_cancel
import org.wit.myassignment.databinding.ActivityTrainerBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber
import timber.log.Timber.i
import timber.log.Timber.log


class TrainerActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityTrainerBinding
    var plan = TrainerModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false

        binding = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        /////has to be moved into the edit function once it's working//////

            binding.planTitle.setText(plan.title)

            binding.btnDeletePlan.setVisibility(View.VISIBLE)

            binding.btnDeletePlan.setOnClickListener() {
                app.plans.delete(plan)

                setResult(RESULT_OK)
                finish()
            }


        if (intent.hasExtra("Plan_Edit")) {
            edit = true
            plan = intent.extras?.getParcelable("Plan_Edit")!!
            binding.planTitle.setText(plan.title)
            binding.btnAdd.setText(R.string.save_plan)
        }



        binding.btnAdd.setOnClickListener() {
            plan.title = binding.planTitle.text.toString()
            if (plan.title.isEmpty()) {
                Snackbar.make(it, R.string.enter_plan_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.plans.update(plan.copy())
                } else {
                    app.plans.create(plan.copy())
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
            item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


