package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.databinding.ActivityTrainerListBinding
import org.wit.myassignment.main.MainApp
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.wit.myassignment.R
import org.wit.myassignment.adapters.PlanListener
import org.wit.myassignment.adapters.TrainerAdapter
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber.i


class TrainerListActivity : AppCompatActivity(), PlanListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTrainerListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


        loadPlans()

        registerRefreshCallback()

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
            R.id.item_add -> {
                i("Button Clicked")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlanClick(plan: TrainerModel) {
        val launcherIntent = Intent(this, TrainerActivity::class.java)
        launcherIntent.putExtra("plan_edit", plan)
        refreshIntentLauncher.launch(launcherIntent)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadPlans() {
        showPlans(app.plans.findAll())
    }

    fun showPlans (plans: List<TrainerModel>) {
        binding.recyclerView.adapter = TrainerAdapter(plans, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadPlans() }
    }

}