package com.score.app.dagger

import android.content.Context
import com.score.app.repository.TeamRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

// Definition of the Application graph
@Singleton
@Component(
        modules = [
            AppModule::class,
            ViewModelModule::class,
            SubComponentsModule::class
        ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun teamComponent(): TeamComponent.Factory
    val teamRepository: TeamRepository
}

@Module(
        subcomponents = [
            TeamComponent::class
        ]
)
object SubComponentsModule