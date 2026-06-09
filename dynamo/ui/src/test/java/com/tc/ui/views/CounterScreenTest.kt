package com.tc.ui.views


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CounterScreenTest {

    @get:Rule(order = 0)
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CounterScreen()
        }
    }


    @Test
    fun `check initial counter is zero`(){

        composeTestRule
            .onNodeWithTag("MAIN_COL")
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription("COUNT_TXT")
            .assertTextEquals("0")

    }

    @Test
    fun `INC CLICK increments the count by 1 `(){
        composeTestRule
            .onNodeWithTag("BTN_INC")
            .performClick()



            composeTestRule
            .onNodeWithContentDescription("COUNT_TXT")
            .assertTextEquals("1")
    }

    @After
    fun tearDown() {

    }

}