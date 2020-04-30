package com.score.app.util

import com.score.app.network.model.Team

fun List<Team>.sortAz(): List<Team> {
    return sortedWith(compareBy { it.fullName })
}

fun List<Team>.sortZa(): List<Team> {
    return sortedWith(compareByDescending { it.fullName })
}

fun List<Team>.sortByWins(): List<Team> {
    return sortedWith(compareBy { it.wins })
}

fun List<Team>.sortByWinsDescending(): List<Team> {
    return sortedWith(compareByDescending { it.wins })
}

fun List<Team>.sortByLosses(): List<Team> {
    return sortedWith(compareBy { it.losses })
}

fun List<Team>.sortByLossesDescending(): List<Team> {
    return sortedWith(compareByDescending { it.losses })
}