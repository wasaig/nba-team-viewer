package com.score.app.viewmodel

import androidx.annotation.VisibleForTesting
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
    private val showRetryButtonLiveData = MutableLiveData<Int>()
    private val showErrorMessageLiveData = MutableLiveData<String>()

    init {
        showProgressBar(1)
        showRetryButton(0)
        fetchTeams()
    }

    fun observeTeams() = teamsLiveData
    fun observeTeamClicked() = teamClickedLiveData
    fun observeProgressBar() = showProgressBarLiveData
    fun observeRetryButton() = showRetryButtonLiveData
    fun observeErrorMessage() = showErrorMessageLiveData

    @VisibleForTesting
    fun fetchTeams() = viewModelScope.launch {
        val resource = repository.fetchTeams()
        showProgressBar(0)
        showRetryButton(0)
        val teams: List<Team> = if (resource.status == Status.SUCCESS) {
            resource.data?.sortAz() ?: emptyList()
        } else {
            showRetryButton(1)
            showErrorMessage(resource.message)
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

    @VisibleForTesting
    fun showProgressBar(value: Int) {
        showProgressBarLiveData.value = value
    }

    @VisibleForTesting
    fun showErrorMessage(message: String?) {
        showErrorMessageLiveData.value = message
    }

    @VisibleForTesting
    fun showRetryButton(value: Int) {
        showRetryButtonLiveData.value = value
    }
}
