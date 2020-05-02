package com.score.app.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.score.app.R
import com.score.app.adapter.TeamAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamDetailsFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(TeamActivity::class.java)

    @Test
    fun testFragmentTitle() {
        onView(withId(R.id.rv_team_list)).perform(actionOnItemAtPosition<TeamAdapter.TeamViewHolder>(2, ViewActions.click()))

        onView(withId(R.id.details_toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.title_team_details)).check(matches(withParent(withId(R.id.details_toolbar))));
    }

    @Test
    fun testTeamInfo() {
        onView(withId(R.id.rv_team_list)).perform(actionOnItemAtPosition<TeamAdapter.TeamViewHolder>(2, ViewActions.click()))

        onView(withId(R.id.team_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_player_list)).check(matches(isDisplayed()))
    }

}