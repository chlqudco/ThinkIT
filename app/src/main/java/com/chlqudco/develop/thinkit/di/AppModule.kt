package com.chlqudco.develop.thinkit.di

import com.chlqudco.develop.thinkit.presentation.concept.ConceptViewModel
import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationViewModel
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsViewModel
import com.chlqudco.develop.thinkit.presentation.main.MainViewModel
import com.chlqudco.develop.thinkit.presentation.multiplechoice.quiz.MultipleChoiceQuizViewModel
import com.chlqudco.develop.thinkit.presentation.multiplechoice.result.MultipleChoiceResultViewModel
import com.chlqudco.develop.thinkit.presentation.quiz.QuizViewModel
import com.chlqudco.develop.thinkit.presentation.subjective.quiz.SubjectiveQuizViewModel
import com.chlqudco.develop.thinkit.presentation.subjective.result.SubjectiveResultViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    //usecase

    //코루틴
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //retrofit

    //레포지토리

    //뷰모델
    viewModel { MainViewModel() }
    viewModel { QuizViewModel() }
    viewModel { ConceptViewModel() }
    viewModel { KeywordsViewModel() }
    viewModel { ExplanationViewModel() }
    viewModel { MultipleChoiceQuizViewModel() }
    viewModel { MultipleChoiceResultViewModel() }
    viewModel { SubjectiveQuizViewModel() }
    viewModel { SubjectiveResultViewModel() }

}