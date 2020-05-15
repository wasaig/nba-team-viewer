package com.score.app.util

import com.score.app.network.model.Team

fun MutableList<Team>.sortAz() {
    this.sortBy { it.fullName }
}

fun MutableList<Team>.sortZa() {
    this.sortByDescending { it.fullName }
}

fun MutableList<Team>.sortByLeastWins() {
    this.sortBy { it.wins }
}

fun MutableList<Team>.sortByMostWins() {
    this.sortByDescending { it.wins }
}

fun MutableList<Team>.sortByLeastLosses() {
    this.sortBy { it.losses }
}

fun MutableList<Team>.sortByMostLosses() {
    this.sortByDescending { it.losses }
}