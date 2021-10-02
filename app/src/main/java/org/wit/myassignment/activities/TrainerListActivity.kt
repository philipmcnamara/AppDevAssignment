package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.databinding.ActivityTrainerListBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.databinding.CardTrainerBinding
import org.wit.myassignment.models.TrainerModel
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.myassignment.R


class TrainerListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTrainerListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = TrainerAdapter(app.plans)
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

}



class TrainerAdapter constructor(private var plans: List<TrainerModel>) :
    RecyclerView.Adapter<TrainerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardTrainerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val plan = plans[holder.adapterPosition]
        holder.bind(plan)
    }

    override fun getItemCount(): Int = plans.size

    class MainHolder(private val binding : CardTrainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: TrainerModel) {
            binding.planTitle.text = plan.title
        }
    }

}