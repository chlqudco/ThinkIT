package com.chlqudco.develop.thinkit.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.chlqudco.develop.thinkit.data.database.provideDao
import com.chlqudco.develop.thinkit.data.database.provideDataBase
import com.chlqudco.develop.thinkit.data.network.*
import com.chlqudco.develop.thinkit.data.network.buildOkHttpClient
import com.chlqudco.develop.thinkit.data.network.provideGsonConverterFactory
import com.chlqudco.develop.thinkit.data.network.provideThinkITApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITFeedbackApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITMyPageApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITQuizApiService
import com.chlqudco.develop.thinkit.data.network.provideThinkITRetrofit
import com.chlqudco.develop.thinkit.data.repository.*
import com.chlqudco.develop.thinkit.domain.concept.*
import com.chlqudco.develop.thinkit.domain.concept.DeleteKeywordDBUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetContentUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsByQueryUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.domain.feedback.PostFeedbackUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetLogInTokenUseCase
import com.chlqudco.develop.thinkit.domain.mypage.SignUpUseCase
import com.chlqudco.develop.thinkit.domain.quiz.GetMultipleQuizUseCase
import com.chlqudco.develop.thinkit.domain.quiz.GetSubjectiveQuizUseCase
import com.chlqudco.develop.thinkit.presentation.csconcept.ConceptViewModel
import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationViewModel
import com.chlqudco.develop.thinkit.presentation.explanationwebview.ExplanationWebViewViewModel
import com.chlqudco.develop.thinkit.presentation.jobconcept.JobConceptViewModel
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsViewModel
import com.chlqudco.develop.thinkit.presentation.main.MainViewModel
import com.chlqudco.develop.thinkit.presentation.mypage.MyPageViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result.MultipleChoiceResultViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.choice.QuizChoiceViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz.ShowIncorrectQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.choice.SubjectiveQuizChoiceViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz.SubjectiveQuizViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.result.SubjectiveResultViewModel
import com.chlqudco.develop.thinkit.presentation.signup.SignUpViewModel
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.DATASTORE_NAME
import com.chlqudco.develop.thinkit.utility.AppKey.SHARED_PREFERENCE_NAME
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
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
    factory { GetLogInTokenUseCase(get()) }
    factory { SignUpUseCase(get()) }
    factory { SendFavoriteKeywordUseCase(get()) }

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
    single { provideThinkITMyPageApiService(get()) }

    //RoomDB
    single { provideDataBase(get()) }
    single { provideDao(get()) }

    //레포지토리
    single<ConceptRepository> { ConceptRepositoryImpl(get(), get(), get()) }
    single<QuizRepository> { QuizRepositoryImpl(get(), get()) }
    single<FeedbackRepository> {FeedbackRepositoryImpl(get(), get())}
    single<MyPageRepository> { MyPageRepositoryImpl(get(), get()) }

    //뷰모델
    viewModel { MainViewModel() }
    viewModel { QuizChoiceViewModel() }
    viewModel { ConceptViewModel(get()) }
    viewModel { KeywordsViewModel(get(), get(), get(), get(), get()) }
    viewModel { ExplanationViewModel(get()) }
    viewModel { MultipleChoiceQuizViewModel(get()) }
    viewModel { MultipleChoiceResultViewModel() }
    viewModel { SubjectiveQuizViewModel(get()) }
    viewModel { SubjectiveResultViewModel() }
    viewModel { ShowIncorrectQuizViewModel() }
    viewModel { ExplanationWebViewViewModel() }
    viewModel { JobConceptViewModel() }
    viewModel { SubjectiveQuizChoiceViewModel() }
    viewModel { MyPageViewModel(get(), get()) }
    viewModel { SignUpViewModel(get()) }


    //dataStore
    single { PreferenceDataStoreFactory.create( produceFile = { androidContext().preferencesDataStoreFile(DATASTORE_NAME) }) }

    //Shared Preference
    single { androidContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE) }
}