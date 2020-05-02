package com.score.app.repository

import com.score.app.network.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TeamRepositoryTest {

    private lateinit var repository: TeamRepository

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun fetchTeams() = runBlocking {
        coEvery { repository.fetchTeams() } returns Resource.success(emptyList())
        repository.fetchTeams()
        coVerify { repository.fetchTeams() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}