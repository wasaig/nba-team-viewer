package com.score.app.dagger

import com.score.app.ui.nbateam.NBATeamFragment
import dagger.Subcomponent

@Subcomponent(modules = [TeamModule::class])
interface TeamComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TeamComponent
    }

    fun inject(fragment: NBATeamFragment)
}