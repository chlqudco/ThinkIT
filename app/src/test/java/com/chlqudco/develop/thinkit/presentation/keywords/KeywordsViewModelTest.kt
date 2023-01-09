package com.chlqudco.develop.thinkit.presentation.keywords

import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.domain.concept.InsertKeywordUseCase
import com.chlqudco.develop.thinkit.presentation.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

/*
 * [KeywordsViewModel]을 테스트 하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item update
 * 4. test Item Delete All
 *
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class KeywordsViewModelTest: ViewModelTest(){

    private val viewModel: KeywordsViewModel by inject()

    private val getKeywordsUseCase: GetKeywordsUseCase by inject()
    private val insertKeywordUseCase: InsertKeywordUseCase by inject()

    private val mockKeywordList = (0 until 10).map {
        KeywordsEntity(
            id = it.toLong(),
            concept = "자료구조",
            keyword = "${it}번째 키워드"
        )
    }

    /*
     * 필요한 Usecase들
     * 1. InsertKeywordsUseCase
     * 2. GetKeywordsUseCase
     */
    // 1.

    @Before
    fun init(){
        initData()
    }

    private fun initData() = runBlockingTest {
        for (keywordEntity in mockKeywordList){
            insertKeywordUseCase(keywordEntity)
        }
    }

    // Test : 입력된 데이터를 불러와서 검증한다.
    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest{
        val testObservable = viewModel._keywordListLiveData.test()
        viewModel.getKeywordByDB("자료구조")
        testObservable.assertValueSequence(
            listOf(
                mockKeywordList.map {
                    it.keyword
                }
            )
        )
    }

}