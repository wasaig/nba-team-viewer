package com.score.app.util

import com.score.app.network.model.Team

fun List<Team>.sortAz(): List<Team> {
    return sortedWith(compareBy { it.fullName })
}

fun List<Team>.sortZa(): List<Team> {
    return sortedWith(compareByDescending { it.fullName })
}

fun List<Team>.sortByLeastWins(): List<Team> {
    return sortedWith(compareBy { it.wins })
}

fun List<Team>.sortByMostWins(): List<Team> {
    return sortedWith(compareByDescending { it.wins })
}

fun List<Team>.sortByLeastLosses(): List<Team> {
    return sortedWith(compareBy { it.losses })
}

fun List<Team>.sortByMostLosses(): List<Team> {
    return sortedWith(compareByDescending { it.losses })
}