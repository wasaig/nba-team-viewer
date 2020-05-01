package com.score.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.score.app.network.Status
import com.score.app.network.model.Team
import com.score.app.repository.TeamRepository
import com.score.app.util.sortAz
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel() {

    private val teamsLiveData = MutableLiveData<List<Team>>()
    private val teamClickedLiveData = MutableLiveData<Team>()
    private val showProgressBarLiveData = MutableLiveData<Int>()
    private val showRetryButton = MutableLiveData<Int>()
    private val showErrorMessage = MutableLiveData<String>()

    init {
        showProgressBarLiveData.value = 1
        showRetryButton.value = 0
        fetchTeams()
    }

    fun observeTeams() = teamsLiveData
    fun observeTeamClicked() = teamClickedLiveData
    fun observeProgressBar() = showProgressBarLiveData
    fun observeRetryButton() = showRetryButton
    fun observeErrorMessage() = showErrorMessage

    private fun fetchTeams() = viewModelScope.launch {
        val resource = repository.fetchTeams()
        showProgressBarLiveData.value = 0
        showRetryButton.value = 0
        val teams: List<Team> = if (resource.status == Status.SUCCESS) {
            resource.data?.sortAz() ?: emptyList()
        } else {
            showRetryButton.value = 1
            showErrorMessage.value = resource.message
            emptyList<Team>()
        }
        teamsLiveData.value = teams
    }

    fun teamClicked(team: Team) {
        teamClickedLiveData.value = team
    }

    fun retryClicked() {
        fetchTeams()
    }
}
