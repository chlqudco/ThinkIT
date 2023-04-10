package com.chlqudco.develop.thinkit

import com.chlqudco.develop.thinkit.data.database.dao.KeywordDaoTest
import com.chlqudco.develop.thinkit.data.network.ConceptApiServiceTest
import com.chlqudco.develop.thinkit.presentation.main.MainActivityTest
import com.chlqudco.develop.thinkit.presentation.splash.SplashActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

// 여러 테스트를 한번에 실행하기 위한 TestSuite
@RunWith(Suite::class)
@ExperimentalCoroutinesApi
@Suite.SuiteClasses(
    MainActivityTest::class,
    KeywordDaoTest::class,
    ConceptApiServiceTest::class,
    SplashActivityTest::class
)
class InstrumentedTestSuite