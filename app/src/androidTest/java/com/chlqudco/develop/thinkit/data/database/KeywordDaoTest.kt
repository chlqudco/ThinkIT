package com.chlqudco.develop.thinkit.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.chlqudco.develop.thinkit.data.database.dao.KeywordDao
import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//dao Test
@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class KeywordDaoTest {

    private lateinit var database: KeywordsDatabase
    private lateinit var dao: KeywordDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            KeywordsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.keywordDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    //잘 들어가는지 확인
    @Test
    fun insert_keyword_to_db() = runTest {
        val testKeyword = KeywordsEntity(
            1, "자료구조", "스택"
        )
        dao.insertKeyword(testKeyword)

        val keywords = dao.getFindKeywords("자료구조")
        assertThat(keywords.contains(testKeyword)).isTrue()
    }

    //잘 삭제되는지 확인

    @Test
    fun delete_keyword_to_db() = runTest {
        val testKeyword = KeywordsEntity(
            1, "자료구조", "스택"
        )
        dao.insertKeyword(testKeyword)
        dao.deleteKeywords("자료구조")

        val keywords = dao.getFindKeywords("자료구조")
        assertThat(keywords.contains(testKeyword)).isFalse()
    }
}