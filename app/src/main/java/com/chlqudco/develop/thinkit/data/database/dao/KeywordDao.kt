package com.chlqudco.develop.thinkit.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity

@Dao
interface KeywordDao {

    //과목에 따른 키워드 찾기 함수
    @Query("SELECT * From KeywordsEntity WHERE concept = :concept")
    suspend fun getFindKeywords(concept: String): List<KeywordsEntity>

    //새로운 키워드 입력
    @Insert
    suspend fun insertKeyword(keyword: KeywordsEntity)

    //해당 과목 모든 키워드 삭제
    @Query("DELETE FROM KeywordsEntity WHERE concept = :concept")
    suspend fun deleteKeywords(concept: String)
}