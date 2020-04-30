package com.score.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.score.app.network.Resource
import com.score.app.network.model.Team
import com.score.app.repository.TeamRepository
import javax.inject.Inject

class NBATeamViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel() {

    private var teamsLiveData: LiveData<Resource<List<Team>>>
    private var teamClickedLiveData = MutableLiveData<Team>()

    init {
        teamsLiveData = fetchTeams()
    }

    fun observeTeams() = teamsLiveData
    fun observeTeamClicked() = teamClickedLiveData

    fun fetchTeams() = liveData {
        val teams = repository.fetchTeams()
        emit(teams)
    }

    fun teamClicked(team: Team) {
        teamClickedLiveData.value = team
    }
}
