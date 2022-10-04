package com.chlqudco.develop.thinkit.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

internal val appModule = module {

    //usecase

    //코루틴
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //retrofit

    //레포지토리

    //뷰모델
}