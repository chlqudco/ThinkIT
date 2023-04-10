package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity

class FakeConceptRepository : ConceptRepository {

    private val keywords = mutableListOf<String>()

    //Retrofit
    override suspend fun getKeywordsList(subject: String): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getContent(keyword: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun sendFavoriteKeyword(
        keyword: String,
        token: String,
        isClicked: Boolean
    ): String {
        TODO("Not yet implemented")
    }

    // DB
    override suspend fun getKeywordsByQuery(concept: String): List<String> {
        return keywords
    }

    override suspend fun deleteKeywords(concept: String) {
        keywords.clear()
    }

    override suspend fun insertKeyword(keyword: KeywordsEntity) {
        keywords.add(keyword.keyword)
    }

}