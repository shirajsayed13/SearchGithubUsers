package com.shiraj.searchgithubusers

import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.shiraj.searchgithubusers.features.result.SearchResultFragmentArgs
import com.shiraj.searchgithubusers.features.search.SearchFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
internal class SearchNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun navigate_from_search_to_search_results() {
        val fragmentArgs = SearchResultFragmentArgs("jake wharton").toBundle()

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        runOnUiThread {
            navController.setGraph(R.navigation.app_navigation)
            navController.setCurrentDestination(R.id.searchFragment, fragmentArgs)
        }

        launchFragmentInHiltContainer<SearchFragment>(fragmentArgs) {
            setViewNavController(it.requireView(), navController)
        }

        assertEquals(R.id.searchFragment, navController.currentDestination?.id)

        onView(withId(R.id.btn_submit)).perform(click())

        assertEquals(R.id.searchResultFragment, navController.currentDestination?.id)
    }
}