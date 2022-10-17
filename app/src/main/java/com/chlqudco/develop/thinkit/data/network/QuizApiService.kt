package com.chlqudco.develop.thinkit.data.network

import com.chlqudco.develop.thinkit.data.response.MultipleChoiceQuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApiService {

    @GET("/api/test")
    suspend fun getMultipleChoice(
        @Query("subjects") subjects: List<String>
    ): Response<MultipleChoiceQuizResponse>

    @GET("/api/interview-problems")
    suspend fun getSubjectiveQuiz(
        @Query("subjects") subjects: List<String>
    ): Response<List<String>>

}