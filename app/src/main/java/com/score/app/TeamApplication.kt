package com.score.app

import android.app.Application
import com.score.app.dagger.AppComponent
import com.score.app.dagger.DaggerAppComponent

open class TeamApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}