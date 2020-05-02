package com.score.app.util

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NetworkUtilTest {

    lateinit var context: Context

    @Before
    fun setUp() {
        context = mockk()
        mockkObject(NetworkUtil.Companion)
        every { NetworkUtil.registerNetworkCallback(context) } answers { NetworkUtil.isConnected = true }
    }

    @Test
    fun registerNetworkCallback() {
        NetworkUtil.registerNetworkCallback(context)
        Assert.assertEquals(NetworkUtil.isConnected, true)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }
}