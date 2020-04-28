package com.score.app

import android.app.Application
import com.score.app.dagger.AppComponent
import com.score.app.dagger.DaggerAppComponent

open class NBATeamApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}