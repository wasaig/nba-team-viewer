package com.score.app.dagger

import androidx.lifecycle.ViewModel
import com.score.app.ui.nbateam.NBATeamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TeamModule {

    @Binds
    @IntoMap
    @ViewModelKey(NBATeamViewModel::class)
    abstract fun bindViewModel(viewModel: NBATeamViewModel): ViewModel
}