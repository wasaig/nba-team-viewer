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
import androidx.lifecycle.lifecycleScope
import com.score.app.R
import com.score.app.TeamApplication
import com.score.app.adapter.TeamAdapter
import com.score.app.network.model.Team
import com.score.app.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_team_list.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamListFragment : Fragment(), TeamAdapter.OnTeamClickListener {

    companion object {
        fun newInstance() = TeamListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by activityViewModels<TeamViewModel> { viewModelFactory }
    private lateinit var teamAdapter: TeamAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as TeamApplication)
                .appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupListAdapter()
        setupRetryButton()

        observeTeams()
        observeProgressBar()
        observeRetryButton()
        observeErrorMessage()
    }

    private fun setupToolbar() {
        list_toolbar.title = getString(R.string.title_team_list)
        list_toolbar.inflateMenu(R.menu.team_menu)
        list_toolbar.overflowIcon = context?.getDrawable(R.drawable.ic_sort)
        list_toolbar.setOnMenuItemClickListener { onMenuItemClick(it) }
    }

    private fun setupListAdapter() {
        teamAdapter = TeamAdapter()
        teamAdapter.teamClickListener = this
        rv_team_list.adapter = teamAdapter
    }

    private fun setupRetryButton() {
        btn_retry.setOnClickListener {
            viewModel.retryClicked()
        }
    }

    private fun observeTeams() {
        viewModel.observeTeams().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                teamAdapter.addData(it)
            }
        })
    }

    private fun observeProgressBar() {
        viewModel.observeProgressBar().observe(viewLifecycleOwner, Observer {
            progress_circular.visibility = it
        })
    }

    private fun observeRetryButton() {
        viewModel.observeRetryButton().observe(viewLifecycleOwner, Observer {
            btn_retry.visibility = it
        })
    }

    private fun observeErrorMessage() {
        viewModel.observeErrorMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onTeamClick(position: Int) {
        val team: Team = teamAdapter.getItemAtPosition(position)
        viewModel.teamClicked(team)
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        lifecycleScope.launch {
            when (item.itemId) {
                R.id.action_A_Z -> teamAdapter.sortAz()
                R.id.action_Z_A -> teamAdapter.sortZa()
                R.id.action_least_wins -> teamAdapter.sortByLeastWins()
                R.id.action_most_wins -> teamAdapter.sortByMostWins()
                R.id.action_least_losses -> teamAdapter.sortByLeastLosses()
                R.id.action_most_losses -> teamAdapter.sortByMostLosses()
            }
        }
        return true
    }

}
