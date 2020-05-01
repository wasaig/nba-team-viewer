package com.score.app.dagger

import com.score.app.TeamActivity
import com.score.app.ui.TeamListFragment
import dagger.Subcomponent

@Subcomponent(modules = [TeamModule::class])
interface TeamComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TeamComponent
    }

    fun inject(fragment: TeamListFragment)
    fun inject(activity: TeamActivity)
}