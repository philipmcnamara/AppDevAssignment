package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.databinding.ActivityTrainerListBinding
import org.wit.myassignment.main.MainApp
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import org.wit.myassignment.R
import org.wit.myassignment.adapters.PlanListener
import org.wit.myassignment.adapters.TrainerAdapter
import org.wit.myassignment.models.PlanStore
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber.i
import java.util.*


class TrainerListActivity : AppCompatActivity(), PlanListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTrainerListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    val plans = ArrayList<TrainerModel>()
    var plansList = ArrayList(plans)


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                plans.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    plansList.forEach{
                        
                        if(it.title.lowercase(Locale.getDefault()).contains(searchText)){

                            plans.add(it)
                        }
                    }
                    binding.recyclerView.adapter!!.notifyDataSetChanged()

                } else{
                    plans.clear()
                    plans.addAll(plansList)
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

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



