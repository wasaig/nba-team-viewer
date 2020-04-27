package com.score.app.ui.nbateam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.score.app.network.Resource
import com.score.app.network.model.Team
import com.score.app.repository.TeamRepository
import javax.inject.Inject

class NBATeamViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel() {


    fun getTeams() = liveData<Resource<ArrayList<Team>>> {
        val teams = repository.getTeams()
        emit(teams)
    }
}
