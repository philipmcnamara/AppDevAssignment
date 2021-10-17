package org.wit.myassignment.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlanMemStore : PlanStore {

    val plans = ArrayList<TrainerModel>()

    override fun findAll() : List<TrainerModel>{
        return plans
    }

    override fun create(plan: TrainerModel){
        plan.id = getId()
        plans.add(plan)
        logAll()
    }

    override fun update(plan: TrainerModel) {
        var foundPlan: TrainerModel? = plans.find { p -> p.id == plan.id }
        if(foundPlan != null){
            foundPlan.title = plan.title
            logAll()
        }
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

}