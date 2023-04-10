package com.chlqudco.develop.thinkit.presentation.main

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

//Robolectric을 사용하는 테스트는 에뮬레이터에서 동작하지 않으므로 이곳에 작성

/*
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    @SmallTest
    fun test_activity_state(){
        val controller = Robolectric.setupActivity(MainActivity::class.java)
        val activityState = controller.lifecycle.currentState.name

        assertThat(activityState).isEqualTo("RESUMED")
    }
}
 */
// 그러나 4.0 이후 부터는 굳이 나눌 필요가 사라짐
// 로보레트릭이 Androidx에 통합되어서 알아서 선택해줌
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_activity_state(){
        val activityState = activityScenarioRule.scenario.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }
}