package org.wit.myassignment.models

import timber.log.Timber.i

class UserMemStore : UserStore {

    val users = ArrayList<Users>()

    override fun findAll(): MutableList<Users> {
        logAll()
        return users
    }

    override fun createUser(user: Users) {
        user.id = getId()
        users.add(user)
        logAll()
    }

    override fun update(user: Users) {
        var foundUser: Users? = users.find { p -> p.id == user.id }
        if(foundUser != null){
            foundUser.name = user.name
            logAll()
        }
    }

    override fun delete(plan: Users) {
        users.remove(plan)
        logAll()
    }

    fun logAll() {
        users.forEach{ i("${it}") }
    }

}