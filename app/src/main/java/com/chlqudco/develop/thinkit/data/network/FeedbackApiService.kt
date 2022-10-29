package com.chlqudco.develop.thinkit.data.network

import com.chlqudco.develop.thinkit.data.response.FeedbackResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query

interface FeedbackApiService {

    @POST("/api/feedback")
    suspend fun postFeedback(
        @Query("feedback") feedback: String
    ): Response<FeedbackResponse>

}