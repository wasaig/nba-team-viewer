package com.score.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.score.app.R
import com.score.app.TeamApplication
import com.score.app.network.model.Team
import com.score.app.util.NetworkUtil
import com.score.app.viewmodel.TeamViewModel
import javax.inject.Inject

class TeamActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<TeamViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        (application as TeamApplication).appComponent.teamComponent().create().inject(this)

        NetworkUtil.registerNetworkCallback(applicationContext)

        if (savedInstanceState == null) {
            loadTeamListFragment()
            observeTeamClicked()
        }
    }

    private fun observeTeamClicked() {
        viewModel.observeTeamClicked().observe(this, Observer {
            loadTeamDetailsFragment(it)
        })
    }

    private fun loadTeamListFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, TeamListFragment.newInstance())
                .commitNow()
    }

    private fun loadTeamDetailsFragment(team: Team) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, TeamDetailsFragment.newInstance(team))
                .addToBackStack(null)
                .commit()
    }
}
