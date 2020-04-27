package com.score.app.ui.nbateam

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.score.app.NBATeamApplication
import com.score.app.R
import javax.inject.Inject

class NBATeamFragment : Fragment() {

    companion object {
        fun newInstance() = NBATeamFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<NBATeamViewModel> { viewModelFactory }

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


    }

}
