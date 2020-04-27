package com.score.app

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.score.app.repository.TeamRepository
import com.score.app.ui.nbateam.NBATeamViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val tasksRepository: TeamRepository,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle) = with(modelClass) {

        when {
            isAssignableFrom(NBATeamViewModel::class.java) -> NBATeamViewModel(tasksRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}