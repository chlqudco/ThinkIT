package com.chlqudco.develop.thinkit.di

import com.chlqudco.develop.thinkit.data.repository.ConceptRepository
import com.chlqudco.develop.thinkit.data.repository.TestConceptRepository
import com.chlqudco.develop.thinkit.domain.concept.*
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    //UseCase
    factory { GetKeywordsUseCase(get()) }
    factory { DeleteKeywordDBUseCase(get()) }
    factory { GetContentUseCase(get()) }
    factory { GetKeywordsByQueryUseCase(get()) }
    factory { InsertKeywordUseCase(get()) }

    //Repository
    single<ConceptRepository> { TestConceptRepository() }

    //ViewModel
    viewModel { KeywordsViewModel(get(), get(), get(), get()) }
}