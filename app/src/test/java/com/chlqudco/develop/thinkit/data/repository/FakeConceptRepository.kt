package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity

class FakeConceptRepository : ConceptRepository {

    private val keywordList = mutableListOf<String>()

    override suspend fun getKeywordsList(subject: String): List<String> {
        return keywordList
    }

    override suspend fun getContent(keyword: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getKeywordsByQuery(concept: String): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteKeywords(concept: String) {
        keywordList.clear()
    }

    override suspend fun insertKeyword(keyword: KeywordsEntity) {
        keywordList.add(keyword.keyword)
    }
}