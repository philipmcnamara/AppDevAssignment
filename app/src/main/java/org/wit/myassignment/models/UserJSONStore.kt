package org.wit.myassignment.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.placemark.helpers.*
import timber.log.Timber
import timber.log.Timber.i
import java.lang.reflect.Type
import java.util.*

const val USER_JSON_FILE = "users.json"
val user_gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val user_listType: Type = object : TypeToken<ArrayList<Users>>() {}.type


 fun generateRandomUserId(): Long {
    return Random().nextLong()
}

class UserJSONStore(private val context: Context) : UserStore {

    var users = mutableListOf<Users>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<Users> {
        logAll()
        return users
    }

    override fun createUser(user: Users) {
        user.id = generateRandomUserId()
        users.add(user)
        serialize()
    }

    override fun findOne(id: Long): Users? {
        var foundUser: Users? = users.find { p -> p.id == id }
        return foundUser
    }

    override fun update(user: Users) {
        var foundPlan = findOne(user.id!!)
        if (foundPlan != null) {
            foundPlan.name = user.name
        }
        serialize()
    }

    override fun delete(user: Users) {
        users.remove(user)
        serialize()
    }

    private fun serialize() {
        val jsonString = user_gsonBuilder.toJson(users, user_listType)
        write(context, USER_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, USER_JSON_FILE)
        users = user_gsonBuilder.fromJson(jsonString, user_listType)
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}

