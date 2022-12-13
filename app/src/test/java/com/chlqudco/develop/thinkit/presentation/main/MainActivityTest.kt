package com.chlqudco.develop.thinkit.presentation.main

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

//로버레트릭 테스트 테스트
//@RunWith(RobolectricTestRunner::class)
//class MainActivityTest{
//
//    @Test
//    @SmallTest
//    fun test_activity_state(){
//        val controller = Robolectric.setupActivity(MainActivity::class.java)
//        val activityState = controller.lifecycle.currentState.name
//        assertThat(activityState).isEqualTo("RESUMED")
//    }
//}

//알아서 결정해준대!
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
}