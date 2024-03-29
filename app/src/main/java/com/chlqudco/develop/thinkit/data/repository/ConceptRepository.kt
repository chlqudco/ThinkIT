package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity


interface ConceptRepository {

    //레트로핏
    suspend fun getKeywordsList(subject: String): List<String>

    suspend fun getContent(keyword: String): String

    //즐겨찾기
    suspend fun sendFavoriteKeyword(keyword: String, token: String, isClicked: Boolean): String


    //DB
    suspend fun getKeywordsByQuery(concept: String): List<String>

    suspend fun deleteKeywords(concept: String)

    suspend fun insertKeyword(keyword: KeywordsEntity)
}