package org.wit.myassignment.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import org.wit.myassignment.helpers.*
import timber.log.Timber.i
import java.nio.file.Files.exists
import java.util.*


val JSON_FILE = "plans.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<TrainerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlanMemStore  : PlanStore {

    var plans = mutableListOf<TrainerModel>()

    init {
        if (org.wit.myassignment.helpers.exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<TrainerModel> {
        return plans
    }

    fun findOne(id: Long) : TrainerModel? {
        var foundPlan: TrainerModel? = plans.find { p -> p.id == id }
        return foundPlan
    }

    override fun create(plan: TrainerModel) {
        plan.id = generateRandomId()
        plans.add(plan)
        serialize()
    }

    override fun update(plan: TrainerModel) {
        var foundPlan = findOne(plan.id!!)
        if (foundPlan != null) {
            foundPlan.title = plan.title
        }
        serialize()
    }

    override fun delete(plan: TrainerModel) {
        val foundPlan: TrainerModel? = plans.find { p -> p.id == plan.id }
        if(foundPlan != null){
            delete(foundPlan)
            logAll()
        }
    }
    fun logAll() {
        plans.forEach{ i("${it}") }
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(plans, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        plans = Gson().fromJson(jsonString, listType)
    }
}