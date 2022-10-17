package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.network.FeedbackApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FeedbackRepositoryImpl(
    private val feedbackApiService: FeedbackApiService,
    private val ioDispatcher: CoroutineDispatcher
): FeedbackRepository {

    override suspend fun sendFeedback(feedback: String): Boolean = withContext(ioDispatcher) {
        try {
            val response = feedbackApiService.postFeedback(feedback)
            if (response.isSuccessful){
                return@withContext response.body() ?: false
            } else{
                return@withContext false
            }
        } catch (exception: Exception){
            return@withContext false
        }
    }
}