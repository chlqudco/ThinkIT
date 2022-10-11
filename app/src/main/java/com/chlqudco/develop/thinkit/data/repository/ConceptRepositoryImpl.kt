package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.network.ConceptApiService
import com.chlqudco.develop.thinkit.data.response.KeywordsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ConceptRepositoryImpl(
    private val conceptApiService: ConceptApiService,
    private val ioDispatcher: CoroutineDispatcher
): ConceptRepository {

    override suspend fun getKeywordsList(subject: String): List<String> = withContext(ioDispatcher) {
        val response = conceptApiService.getKeywords(subject)
        return@withContext if (response.isSuccessful){
            response.body()!!.keywordsList
        } else{
            listOf()
        }
    }
}