package com.score.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.score.app.CoroutineTestRule
import com.score.app.network.Resource
import com.score.app.network.model.Team
import com.score.app.repository.TeamRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

@ExperimentalCoroutinesApi
class TeamViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: TeamRepository

    private lateinit var viewModel: TeamViewModel

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery { repository.fetchTeams() } returns Resource.success(emptyList<Team>())
        viewModel = TeamViewModel(repository)
    }

    @Test
    fun observeTeams() {
        val mockedObserver = spyk<Observer<List<Team>>>()
        viewModel.observeTeams().observeForever(mockedObserver)

        viewModel.fetchTeams()

        val slot = slot<List<Team>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, emptyList<Team>())
        coVerify { repository.fetchTeams() }
    }

    @Test
    fun observeTeamClicked() {
        val mockedObserver = spyk<Observer<Team>>()
        viewModel.observeTeamClicked().observeForever(mockedObserver)

        val team = mockk<Team>()
        viewModel.teamClicked(team)

        val slot = slot<Team>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, team)
    }

    @Test
    fun observeProgressBar() {
        val mockedObserver = spyk<Observer<Int>>()
        viewModel.observeProgressBar().observeForever(mockedObserver)

        viewModel.showProgressBar(1)

        val slot = slot<Int>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, 1)
    }

    @Test
    fun observeRetryButton() {
        val mockedObserver = spyk<Observer<Int>>()
        viewModel.observeRetryButton().observeForever(mockedObserver)

        viewModel.showRetryButton(1)

        val slot = slot<Int>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, 1)
    }

    @Test
    fun observeErrorMessage() {
        val mockedObserver = spyk<Observer<String>>()
        viewModel.observeErrorMessage().observeForever(mockedObserver)

        val msg = "Something went wrong"
        viewModel.showErrorMessage(msg)

        val slot = slot<String>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, msg)
    }

    @Test
    fun teamClicked() {
        val mockedObserver = spyk<Observer<Team>>()
        viewModel.observeTeamClicked().observeForever(mockedObserver)

        val team = mockk<Team>()
        viewModel.teamClicked(team)

        val slot = slot<Team>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, team)
    }

    @Test
    fun retryClicked() {
        val mockedObserver = spyk<Observer<List<Team>>>()
        viewModel.observeTeams().observeForever(mockedObserver)

        viewModel.fetchTeams()

        val slot = slot<List<Team>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Assert.assertEquals(slot.captured, emptyList<Team>())
        coVerify { repository.fetchTeams() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}