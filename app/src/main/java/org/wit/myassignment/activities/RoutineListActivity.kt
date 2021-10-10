package org.wit.myassignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.R
import org.wit.myassignment.adapters.RoutineAdapter
import org.wit.myassignment.adapters.RoutineListner
import org.wit.myassignment.databinding.ActivityPlansListBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.exerciseModel


class RoutineListActivity : AppCompatActivity(), RoutineListner {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPlansListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlansListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarRoutine.title = title
        setSupportActionBar(binding.toolbarRoutine)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRoutine.layoutManager = layoutManager
        binding.recyclerViewRoutine.adapter = RoutineAdapter(app.routines.findAll(),this)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, RoutineActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlanClick(plan: exerciseModel) {
        val launcherIntent = Intent(this, RoutineActivity::class.java)
        launcherIntent.putExtra("plan_edit", plan)
        startActivityForResult(launcherIntent, 0)
    }

}