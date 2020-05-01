package com.score.app.dagger

import androidx.lifecycle.ViewModel
import com.score.app.viewmodel.TeamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TeamModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    abstract fun bindViewModel(viewModel: TeamViewModel): ViewModel
}