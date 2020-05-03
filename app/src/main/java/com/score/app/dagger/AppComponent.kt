package com.score.app.dagger

import android.content.Context
import com.score.app.ui.TeamActivity
import com.score.app.ui.TeamListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            ViewModelModule::class,
            TeamModule::class
        ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(fragment: TeamListFragment)
    fun inject(activity: TeamActivity)

}
