package com.score.app.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.score.app.R
import com.score.app.adapter.TeamAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamListFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(TeamActivity::class.java)

    @Test
    fun testFragmentTitle() {
        onView(withId(R.id.list_toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.title_team_list)).check(matches(withParent(withId(R.id.list_toolbar))));
    }

    @Test
    fun testSortMenu() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.action_a_z)).check(matches(isDisplayed()))
        onView(withText(R.string.action_z_a)).check(matches(isDisplayed()))
        onView(withText(R.string.action_losses)).check(matches(isDisplayed()))
        onView(withText(R.string.action_losses_descending)).check(matches(isDisplayed()))
        onView(withText(R.string.action_wins)).check(matches(isDisplayed()))
        onView(withText(R.string.action_wins_descending)).check(matches(isDisplayed()))
    }

    @Test
    fun testSortMenuClicked() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.action_losses)).perform(click())
    }

    @Test
    fun testTeamClick() {
        onView(withId(R.id.rv_team_list)).perform(actionOnItemAtPosition<TeamAdapter.TeamViewHolder>(2, click()))
    }
}