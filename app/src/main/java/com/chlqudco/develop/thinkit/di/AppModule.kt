package com.chlqudco.develop.thinkit.di

import com.chlqudco.develop.thinkit.data.database.provideDao
import com.chlqudco.develop.thinkit.data.database.provideDataBase
import com.chlqudco.develop.thinkit.data.network.*
import com.chlqudco.develop.thinkit.data.network.buildOkHttpClient
import com.chlqudco.develop.thinkit.data.network.provideGsonConverterFactory
import com.chlqudco.develop.thinkit.data.network.provideThinkITApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITFeedbackApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITQuizApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITRetrofit
import com.chlqudco.develop.thinkit.data.repository.*
import com.chlqudco.develop.thinkit.domain.concept.*
import com.chlqudco.develop.thinkit.domain.concept.DeleteKeywordDBUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetContentUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsByQueryUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.domain.feedback.PostFeedbackUseCase
import com.chlqudco.develop.thinkit.domain.quiz.GetMultipleQuizUseCase
import com.chlqudco.develop.thinkit.domain.quiz.GetSubjectiveQuizUseCase
import com.chlqudco.develop.thinkit.presentation.concept.ConceptViewModel
import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationViewModel
import com.chlqudco.develop.thinkit.presentation.explanationwebview.ExplanationWebViewViewModel
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsViewModel
import com.chlqudco.develop.thinkit.presentation.main.MainViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result.MultipleChoiceResultViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.QuizChoiceViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz.ShowIncorrectQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz.SubjectiveQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.result.SubjectiveResultViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    // UseCase
    factory { GetKeywordsUseCase(get()) }
    factory { GetContentUseCase(get()) }
    factory { GetMultipleQuizUseCase(get()) }
    factory { GetSubjectiveQuizUseCase(get()) }
    factory { PostFeedbackUseCase(get()) }
    factory { DeleteKeywordDBUseCase(get()) }
    factory { GetKeywordsByQueryUseCase(get()) }
    factory { InsertKeywordUseCase(get()) }

    //코루틴
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //retrofit
    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }
    single { provideThinkITApiService(get()) }
    single { provideThinkITRetrofit(get(), get()) }
    single { provideThinkITQuizApiService(get()) }
    single { provideThinkITFeedbackApiService(get()) }

    //RoomDB
    single { provideDataBase(get()) }
    single { provideDao(get()) }

    //레포지토리
    single<ConceptRepository> { ConceptRepositoryImpl(get(), get(), get()) }
    single<QuizRepository> { QuizRepositoryImpl(get(), get()) }
    single<FeedbackRepository> {FeedbackRepositoryImpl(get(), get())}

    //뷰모델
    viewModel { MainViewModel() }
    viewModel { QuizChoiceViewModel() }
    viewModel { ConceptViewModel(get()) }
    viewModel { KeywordsViewModel(get(), get(), get(), get()) }
    viewModel { ExplanationViewModel(get()) }
    viewModel { MultipleChoiceQuizViewModel(get()) }
    viewModel { MultipleChoiceResultViewModel() }
    viewModel { SubjectiveQuizViewModel(get()) }
    viewModel { SubjectiveResultViewModel() }
    viewModel { ShowIncorrectQuizViewModel() }
    viewModel { ExplanationWebViewViewModel() }

}