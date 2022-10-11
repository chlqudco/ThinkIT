package com.chlqudco.develop.thinkit.di

import com.chlqudco.develop.thinkit.data.network.buildOkHttpClient
import com.chlqudco.develop.thinkit.data.network.provideGsonConverterFactory
import com.chlqudco.develop.thinkit.data.network.provideThinkITApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITRetrofit
import com.chlqudco.develop.thinkit.data.repository.ConceptRepository
import com.chlqudco.develop.thinkit.data.repository.ConceptRepositoryImpl
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.presentation.concept.ConceptViewModel
import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationViewModel
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsViewModel
import com.chlqudco.develop.thinkit.presentation.main.MainViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result.MultipleChoiceResultViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.QuizChoiceViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz.SubjectiveQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.result.SubjectiveResultViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    // UseCase
    factory { GetKeywordsUseCase(get()) }

    //코루틴
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //retrofit
    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }
    single { provideThinkITApiService(get()) }
    single { provideThinkITRetrofit(get(), get()) }

    //레포지토리
    single<ConceptRepository> { ConceptRepositoryImpl(get(), get()) }

    //뷰모델
    viewModel { MainViewModel() }
    viewModel { QuizChoiceViewModel() }
    viewModel { ConceptViewModel() }
    viewModel { KeywordsViewModel(get()) }
    viewModel { ExplanationViewModel() }
    viewModel { MultipleChoiceQuizViewModel() }
    viewModel { MultipleChoiceResultViewModel() }
    viewModel { SubjectiveQuizViewModel() }
    viewModel { SubjectiveResultViewModel() }

}