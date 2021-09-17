package com.shiraj.searchgithubusers.features.search

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shiraj.searchgithubusers.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_SearchGithubUsers)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testSubmitButton() {
        val searchKeyword = "jake wharton"
        Espresso.onView(withId(R.id.edt_login)).perform(ViewActions.typeText(searchKeyword))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.btn_submit)).perform(ViewActions.click())
    }

}