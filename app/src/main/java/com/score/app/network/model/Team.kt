package com.score.app.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(val wins: Int,
                val losses: Int,
                @SerializedName("full_name") val fullName: String,
                val id: Int,
                val players: ArrayList<Player>) : Parcelable
