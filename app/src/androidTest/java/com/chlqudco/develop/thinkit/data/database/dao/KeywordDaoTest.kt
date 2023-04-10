package com.chlqudco.develop.thinkit.data.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.chlqudco.develop.thinkit.data.database.KeywordsDatabase
import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class KeywordDaoTest {

    private lateinit var database: KeywordsDatabase
    private lateinit var dao: KeywordDao

    @Before
    fun setUp() {
        // 메모리 안에서 생성하여 테스트가 끝나면 파괴되도록 함
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            KeywordsDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        dao = database.keywordDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    //insertKeyword TEst
    @Test
    fun insert_keyword_test() = runTest {
        val keyword = KeywordsEntity(
            id = 0,
            concept = "자료구조",
            keyword = "스택"
        )
        dao.insertKeyword(keyword)

        val keywords = dao.getFindKeywords("자료구조").first().keyword

        assertThat(keywords).matches("스택")
    }

    //deleteKeywords Test
    @Test
    fun delete_keywords_test() = runTest {
        val keyword = KeywordsEntity(
            id = 0,
            concept = "자료구조",
            keyword = "스택"
        )
        dao.insertKeyword(keyword)

        dao.deleteKeywords("자료구조")
        
        val keywords = dao.getFindKeywords("자료구조")

        assertThat(keywords).isEmpty()
    }

}