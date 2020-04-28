package com.score.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.score.app.NBATeamApplication
import com.score.app.R
import com.score.app.adapter.TeamAdapter
import com.score.app.network.Status
import com.score.app.network.model.Team
import com.score.app.viewmodel.NBATeamViewModel
import kotlinx.android.synthetic.main.n_b_a_team_fragment.*
import javax.inject.Inject

class TeamListFragment : Fragment(), TeamAdapter.OnTeamClickListener {

    companion object {
        fun newInstance() = TeamListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<NBATeamViewModel> { viewModelFactory }
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

        setupListAdapter()
        setupRetryButton()
        observeTeams()
    }

    private fun setupListAdapter() {
        teamAdapter = TeamAdapter()
        teamAdapter.teamClickListener = this
        rv_team_list.adapter = teamAdapter
    }

    private fun setupRetryButton() {
        btn_retry.setOnClickListener {
            progress_circular.visibility = View.VISIBLE
            viewModel.fetchTeams()
        }
    }

    private fun observeTeams() {
        progress_circular.visibility = View.VISIBLE
        viewModel.observeTeams().observe(viewLifecycleOwner, Observer {
            progress_circular.visibility = View.GONE
            if (it.status == Status.SUCCESS) {
                it.data?.let { teams -> teamAdapter.addData(teams) }
            } else {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                btn_retry.visibility = View.VISIBLE
            }
        })
    }

    override fun onTeamClick(position: Int) {
        val team: Team = teamAdapter.getItemAtPosition(position)
    }

}
