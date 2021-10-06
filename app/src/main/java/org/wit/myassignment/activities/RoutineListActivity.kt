package org.wit.myassignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.R
import org.wit.myassignment.adapters.PlanListner
import org.wit.myassignment.adapters.TrainerAdapter
import org.wit.myassignment.databinding.ActivityPlansBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.TrainerModel



class RoutineListActivity : AppCompatActivity(), PlanListner {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPlansBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = TrainerAdapter(app.plans.findAll(),this)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, TrainerActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlanClick(plan: TrainerModel) {
        val launcherIntent = Intent(this, TrainerActivity::class.java)
        launcherIntent.putExtra("plan_edit", plan)
        startActivityForResult(launcherIntent, 0)
    }

}
