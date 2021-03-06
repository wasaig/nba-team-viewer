package com.score.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.score.app.R
import com.score.app.adapter.PlayerAdapter
import com.score.app.network.model.Team
import kotlinx.android.synthetic.main.fragment_team_details.*
import kotlinx.android.synthetic.main.team_list_item.*


private const val ARG_TEAM = "arg_team"

class TeamDetailsFragment : Fragment() {

    private var team: Team? = null

    companion object {
        @JvmStatic
        fun newInstance(team: Team) =
                TeamDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_TEAM, team)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            team = it.getParcelable(ARG_TEAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupToolbar()
        setupTeamInfo()
        setupPlayerList()
    }

    private fun setupToolbar() {
        details_toolbar.title = getString(R.string.title_team_details)
        details_toolbar.setNavigationIcon(R.drawable.ic_back)
        details_toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun setupTeamInfo() {
        tv_team_name.text = team?.fullName
        tv_team_wins.text = String.format(getString(R.string.team_wins), team?.wins)
        tv_team_losses.text = String.format(getString(R.string.team_losses), team?.losses)
    }

    private fun setupPlayerList() {
        val playerAdapter = PlayerAdapter()
        team?.players?.let { playerAdapter.addData(it) }
        rv_player_list.adapter = playerAdapter
    }

}
