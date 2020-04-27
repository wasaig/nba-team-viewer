package com.score.app.dagger

import com.score.app.repository.TeamRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repository: TeamRepository): TeamRepository
}