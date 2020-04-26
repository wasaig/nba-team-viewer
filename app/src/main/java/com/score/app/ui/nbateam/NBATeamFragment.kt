package com.score.app.ui.nbateam

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.score.app.R

class NBATeamFragment : Fragment() {

    companion object {
        fun newInstance() = NBATeamFragment()
    }

    private lateinit var viewModel: NBATeamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.n_b_a_team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NBATeamViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
