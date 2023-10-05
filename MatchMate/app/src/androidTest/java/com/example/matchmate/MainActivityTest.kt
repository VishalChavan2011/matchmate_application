package com.example.matchmate

import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import androidx.test.ext.junit.rules.activityScenarioRule


@RunWith(AndroidJUnit4::class)
 class MainActivityTest {

    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testAssert() {
        assertEquals(4, 2 + 2)
    }
}