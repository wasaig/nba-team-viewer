package com.score.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.score.app.network.model.Team
import com.score.app.ui.TeamDetailsFragment
import com.score.app.ui.TeamListFragment
import com.score.app.viewmodel.NBATeamViewModel
import javax.inject.Inject

class NBATeamActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<NBATeamViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.n_b_a_team_activity)
        (application as NBATeamApplication).appComponent.teamComponent().create().inject(this)

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
