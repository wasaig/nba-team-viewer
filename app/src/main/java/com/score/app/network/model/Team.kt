package com.score.app.network.model

import com.google.gson.annotations.SerializedName

data class Team(val wins: Int,
                val losses: Int,
                @SerializedName("full_name") val fullName: String,
                val id: Int,
                val players: ArrayList<Player>)
