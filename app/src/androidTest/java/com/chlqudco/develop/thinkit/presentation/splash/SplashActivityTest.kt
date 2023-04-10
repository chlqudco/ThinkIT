package com.chlqudco.develop.thinkit.presentation.splash

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<SplashActivity> = ActivityScenarioRule(SplashActivity::class.java)


    @Test
    @LargeTest
    fun ui_test_example(){

        // 화면 잘 켜지는지 확인
        onView(withId(R.id.ActivitySplashTitleTextView))
            .check(matches(isDisplayed()))

        // 한 2초 기다리기
        onView(isRoot())
            .perform(waitFor(2000))

        // 메인 화면이 잘 켜지는지 확인
        onView(withId(R.id.ActivityMainTitleTextView))
            .check(matches(isDisplayed()))

        // 한 2초 기다리기
        onView(isRoot())
            .perform(waitFor(2000))

        // 최병채 버튼 클릭
        onView(withId(R.id.FragmentQuizChoiceAndroidCheckBox))
            .perform(click())

        // 시작 버튼 클릭
        onView(withId(R.id.FragmentSubjectiveQuizChoiceStartButton))
            .perform(click())

        // 문제 화면으로 잘 이동하는지 확인. 버튼이 나타났는지 보면 됨
        onView(withId(R.id.ActivitySubjectiveQuizShowBestAnswerButton))
            .check(matches(isDisplayed()))

    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String = "wait for $delay milliseconds"

            override fun getConstraints(): Matcher<View> = isRoot()

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }

        }
    }
}