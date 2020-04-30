package com.score.app.network.service

import com.score.app.network.model.Team
import retrofit2.http.GET

interface TeamService {

    @GET("nba-team-viewer/master/input.json")
    suspend fun getTeams(): List<Team>
}