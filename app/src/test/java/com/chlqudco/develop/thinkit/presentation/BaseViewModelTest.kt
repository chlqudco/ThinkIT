package com.chlqudco.develop.thinkit.presentation

import android.app.Application
import com.chlqudco.develop.thinkit.di.appTestModule
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock

internal abstract class BaseViewModelTest: KoinTest {

    @Mock
    private lateinit var context: Application

    @Before
    fun setUp(){
        startKoin {
            androidContext(context)
            modules(appTestModule)
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}