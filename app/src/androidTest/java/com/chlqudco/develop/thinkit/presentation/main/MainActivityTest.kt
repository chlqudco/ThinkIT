package com.chlqudco.develop.thinkit.presentation.main

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.common.truth.Truth.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.chlqudco.develop.thinkit.R

//계측 테스트 테스트
@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    internal var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_activity_state(){
        val activityState = activityScenarioRule.scenario.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }

    //UI 테스트
    @Test
    @LargeTest
    fun from_ConceptFragment_to_KeywordsFragment_Ui_Operation(){
        // 1. ConceptFragment
        // 1-1. conceptFragment 로딩 확인
        onView(withId(R.id.FragmentConceptDataStructureLayout)).perform(click())
        // 1-2. 자료구조 버튼 클릭

        // 2. KeywordsFragment
        onView(isRoot()).perform(waitFor(3000))
        // 2-1. 키워드 나오는지 확인
        onView(withId(R.id.FragmentKeywordsRecyclerView)).check(matches(isDisplayed()))
    }

    private fun waitFor(delay: Long): ViewAction{
        return object : ViewAction{

            override fun getDescription(): String = "wait for $delay milliseconds"

            override fun getConstraints(): Matcher<View> = isRoot()

            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(delay)
            }

        }
    }
}