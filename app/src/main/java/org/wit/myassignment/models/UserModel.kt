package org.wit.myassignment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(var id: Long = 0,
                 var name: String = "",
                 var email: String = "",
                 var password: String = "") : Parcelable