package com.score.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.score.app.NBATeamApplication
import com.score.app.R
import com.score.app.adapter.TeamAdapter
import com.score.app.network.Status
import com.score.app.network.model.Team
import com.score.app.util.sortAz
import com.score.app.viewmodel.NBATeamViewModel
import kotlinx.android.synthetic.main.n_b_a_team_fragment.*
import javax.inject.Inject

class TeamListFragment : Fragment(), TeamAdapter.OnTeamClickListener {

    companion object {
        fun newInstance() = TeamListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by activityViewModels<NBATeamViewModel> { viewModelFactory }
    private lateinit var teamAdapter: TeamAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as NBATeamApplication)
                .appComponent.teamComponent().create().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.n_b_a_team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupListAdapter()
        setupRetryButton()

        progress_circular.visibility = View.VISIBLE
        observeTeams()
    }

    private fun setupToolbar() {
        list_toolbar.title = getString(R.string.title_team_list)
        list_toolbar.inflateMenu(R.menu.team_menu)
        list_toolbar.setOnMenuItemClickListener { onMenuItemClick(it) }
    }

    private fun setupListAdapter() {
        teamAdapter = TeamAdapter()
        teamAdapter.teamClickListener = this
        rv_team_list.adapter = teamAdapter
    }

    private fun setupRetryButton() {
        btn_retry.setOnClickListener {
            btn_retry.visibility = View.GONE
            progress_circular.visibility = View.VISIBLE
            viewModel.fetchTeams()
        }
    }

    private fun observeTeams() {
        viewModel.observeTeams().observe(viewLifecycleOwner, Observer {
            progress_circular.visibility = View.GONE
            if (it.status == Status.SUCCESS) {
                it.data?.let { teams ->
                    val sortedTeams = teams.sortAz()
                    teamAdapter.addData(sortedTeams)
                }
            } else {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                btn_retry.visibility = View.VISIBLE
            }
        })
    }

    override fun onTeamClick(position: Int) {
        val team: Team = teamAdapter.getItemAtPosition(position)
        viewModel.teamClicked(team)
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_A_Z -> teamAdapter.sortAz()
            R.id.action_Z_A -> teamAdapter.sortZa()
            R.id.action_wins -> teamAdapter.sortByWins()
            R.id.action_wins_descending -> teamAdapter.sortByWinsDescending()
            R.id.action_losses -> teamAdapter.sortByLosses()
            R.id.action_losses_descending -> teamAdapter.sortByLossesDescending()
        }
        return true
    }

}
