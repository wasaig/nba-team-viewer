package com.score.app.repository

import com.score.app.network.Resource
import com.score.app.network.ResponseHandler
import com.score.app.network.model.Team
import com.score.app.network.service.TeamService
import javax.inject.Inject

class TeamRepository @Inject constructor(private val service: TeamService,
                                         private val responseHandler: ResponseHandler) {

    suspend fun getTeams(): Resource<ArrayList<Team>> {
        return try {
            responseHandler.handleSuccess(service.getTeams())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}