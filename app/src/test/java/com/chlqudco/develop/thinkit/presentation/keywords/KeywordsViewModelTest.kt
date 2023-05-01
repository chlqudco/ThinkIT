package com.chlqudco.develop.thinkit.presentation.keywords

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.chlqudco.develop.thinkit.data.database.KeywordsDatabase
import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.domain.concept.InsertKeywordUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModelTest
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATA_STRUCTURE
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class KeywordsViewModelTest: BaseViewModelTest(){

    private val viewModel : KeywordsViewModel by inject()

    private val insertKeywordUseCase: InsertKeywordUseCase by inject()
    private val getKeywordsUseCase: GetKeywordsUseCase by inject()

    private val testKeyword = KeywordsEntity(
        id = 0,
        concept = KEYWORD_DATA_STRUCTURE,
        keyword = "스택"
    )

    @Before
    fun init(){
        initData()
    }

    private fun initData() = runTest{
        insertKeywordUseCase(testKeyword)
    }

    @Test
    fun test_get_keywords_by_db() = runTest {
        viewModel.getKeywordByDB(KEYWORD_DATA_STRUCTURE)
        val result = getKeywordsUseCase(KEYWORD_DATA_STRUCTURE)
        assertThat(result).contains("스택")
    }

}