package com.chlqudco.develop.thinkit.data.repository

import android.util.Log
import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity
import com.chlqudco.develop.thinkit.data.network.ConceptApiService
import com.chlqudco.develop.thinkit.data.response.MultipleChoiceQuizResponse
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
            Log.e("ERROOR",exception.message ?: "")
            return@withContext listOf()
        }
    }

    override suspend fun getContent(keyword: String): String = withContext(ioDispatcher) {
        try {
            val response = conceptApiService.getContent(keyword)
            return@withContext if (response.isSuccessful){
                response.body()?.content ?: ""
            } else{
                ""
            }
        } catch (exception: Exception){
            return@withContext ""
        }
    }


}