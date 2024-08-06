package com.siddharthchordia.myrepoapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var mockWebServer: MockWebServer
    val mockUserProfileResponse = """
            {
              "name": "The Octocat",
              "avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4"
            }
    """.trimIndent()
    val mockResponseRepoList = """
            [
            {
                "name": "Repo 1",
                "description": "Description 1",
                "updated_at": "2022-01-01",
                "stargazers_count": 100,
                "forks": 50
            },
            {
                "name": "Repo 2",
                "description": "Description 2",
                "updated_at": "2022-01-02",
                "stargazers_count": 200,
                "forks": 100
            }
            ]
    """.trimIndent()

    @Before
    fun setUp() {
        hiltRule.inject()
        // Set up MockWebServer by providing a Dispatcher that will return the mock response for the corresponding request
        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users/octocat" -> MockResponse()
                        .setBody(mockUserProfileResponse)
                        .setResponseCode(200)
                    "/users/octocat/repos" -> MockResponse()
                        .setBody(mockResponseRepoList)
                        .setResponseCode(200)
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSearchAndRepoDetails() {
        val count = mockWebServer.requestCount
        println("Request count: $count")
        composeTestRule.onNodeWithTag("searchTextField").performTextInput("octocat")
        composeTestRule.onNodeWithTag("searchButton", useUnmergedTree = true).assertExists()
            .performClick()

        val count2 = mockWebServer.requestCount
        println("Request count: $count2")
        composeTestRule.waitUntil(timeoutMillis = 5000L) {
            composeTestRule.onNodeWithText("Repo 1").isDisplayed()
        }

        composeTestRule.onNodeWithText("Repo 1").assertIsDisplayed()
    }
}
