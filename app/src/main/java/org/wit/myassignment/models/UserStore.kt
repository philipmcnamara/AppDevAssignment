package org.wit.myassignment.models

interface UserStore {
    fun findAll(): List<Users>
    fun createUser(user: Users)
    fun update(user: Users)
    fun delete(user: Users)

}