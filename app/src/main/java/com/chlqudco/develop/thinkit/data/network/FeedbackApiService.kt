package com.chlqudco.develop.thinkit.data.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface FeedbackApiService {

    @POST("/api/feedback")
    suspend fun postFeedback(
        @Field("feedback") feedback: String
    ): Response<Boolean>

}