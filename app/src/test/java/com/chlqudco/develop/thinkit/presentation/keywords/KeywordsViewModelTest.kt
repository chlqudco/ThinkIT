package com.chlqudco.develop.thinkit.presentation.keywords

import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import com.chlqudco.develop.thinkit.domain.concept.InsertKeywordUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModelTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

@OptIn(ObsoleteCoroutinesApi::class)
@ExperimentalCoroutinesApi
class KeywordsViewModelTest: BaseViewModelTest() {

    private val keywordsViewModel: KeywordsViewModel by inject()

    private val insertKeywordUseCase: InsertKeywordUseCase by inject()

    private val keyword = KeywordsEntity(
        id = 0,
        concept = "자료구조",
        keyword = "스택"
    )

    @Before
    fun setUp(){
        initData()
    }

    private fun initData() = runTest {
        insertKeywordUseCase(keyword)
    }

    @Test
    fun insert_keyword_test() = runTest {

        delay(2000)

        keywordsViewModel.getKeywordByDB("자료구조")

        delay(2000)


    }
}