package com.example.matchmate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.matchmate.repository.NetworkResult
import com.example.matchmate.repository.NetworkResult.*
import com.example.matchmate.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import net.bytebuddy.agent.VirtualMachine.ForOpenJ9.Dispatcher
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProfileViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var repository: ProfileRepository
    private val testDispatcher = StandardTestDispatcher()




    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_fetchData() = runTest {
        Mockito.`when`(repository.getProfileInformation(true)).then {
            repository.networkResultLiveData.value
        }
    }

    @After
    fun tearDown() {
    }
}