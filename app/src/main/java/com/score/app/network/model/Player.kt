package com.score.app.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(val id: Int,
                  @SerializedName("first_name") val firstName: String,
                  @SerializedName("last_name") val lastName: String,
                  val position: String,
                  val number: Int) : Parcelable
