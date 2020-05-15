package com.score.app.viewmodel

import android.view.View
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
        showProgressBar(View.VISIBLE)
        showRetryButton(View.GONE)
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
        showProgressBar(View.GONE)
        showRetryButton(View.GONE)
        val teams: List<Team> = if (resource.status == Status.SUCCESS) {
            val data = resource.data
            data?.sortAz()
            data ?: emptyList()
        } else {
            showRetryButton(View.VISIBLE)
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
