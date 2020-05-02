package com.score.app.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamActivityTest {

    @Test
    fun testEventActivity() {
        val scenario = launchActivity<TeamActivity>()
        scenario.moveToState(Lifecycle.State.CREATED)
    }

}