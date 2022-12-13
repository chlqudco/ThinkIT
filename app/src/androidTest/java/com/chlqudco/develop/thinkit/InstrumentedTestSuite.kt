package com.chlqudco.develop.thinkit

import com.chlqudco.develop.thinkit.data.database.KeywordDaoTest
import com.chlqudco.develop.thinkit.presentation.main.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

//한꺼번에 실행하자!
@RunWith(Suite::class)
@ExperimentalCoroutinesApi
@Suite.SuiteClasses(
    MainActivityTest::class,
    KeywordDaoTest::class,
)
class InstrumentedTestSuite