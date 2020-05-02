package com.score.app.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.score.app.network.model.Team
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExtensionsKtTest {

    private lateinit var teams: List<Team>

    @Before
    fun setUp() {
        val json = "[\n" +
                "  {\n" +
                "    \"wins\": 45,\n" +
                "    \"losses\": 20,\n" +
                "    \"full_name\": \"Boston Celtics\",\n" +
                "    \"id\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"wins\": 20,\n" +
                "    \"losses\": 44,\n" +
                "    \"full_name\": \"Brooklyn Nets\",\n" +
                "    \"id\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"wins\": 34,\n" +
                "    \"losses\": 28,\n" +
                "    \"full_name\": \"Philadelphia 76ers\",\n" +
                "    \"id\": 4\n" +
                "  }\n" +
                "]"

        teams = Gson().fromJson(json, object : TypeToken<List<Team>>() {}.type)
    }

    @Test
    fun sortAz() {
        val sortedTeams = this.teams.sortAz()
        Assert.assertEquals(sortedTeams[0].fullName, "Boston Celtics")
    }

    @Test
    fun sortZa() {
        val sortedTeams = this.teams.sortZa()
        Assert.assertEquals(sortedTeams[0].fullName, "Philadelphia 76ers")
    }

    @Test
    fun sortByLeastWins() {
        val sortedTeams = this.teams.sortByLeastWins()
        Assert.assertEquals(sortedTeams[0].fullName, "Brooklyn Nets")
    }

    @Test
    fun sortByMostWins() {
        val sortedTeams = this.teams.sortByMostWins()
        Assert.assertEquals(sortedTeams[0].fullName, "Boston Celtics")
    }

    @Test
    fun sortByLeastLosses() {
        val sortedTeams = this.teams.sortByLeastLosses()
        Assert.assertEquals(sortedTeams[0].fullName, "Boston Celtics")
    }

    @Test
    fun sortByMostLosses() {
        val sortedTeams = this.teams.sortByMostLosses()
        Assert.assertEquals(sortedTeams[0].fullName, "Brooklyn Nets")
    }
}