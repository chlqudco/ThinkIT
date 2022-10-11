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
        try {
            val response = conceptApiService.getKeywords(subject)
            return@withContext if (response.isSuccessful){
                response.body()?.keywordsList ?: listOf()
            } else{
                listOf()
            }
        } catch (exception: Exception){
            return@withContext listOf("오류","가","발생했","습니다","111","111","111","111","111","111","111","111","111")
        }

    }
}