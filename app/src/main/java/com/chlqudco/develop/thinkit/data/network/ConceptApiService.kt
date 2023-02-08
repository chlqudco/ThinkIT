package com.chlqudco.develop.thinkit.data.network

import com.chlqudco.develop.thinkit.data.response.ContentResponse
import com.chlqudco.develop.thinkit.data.response.KeywordsResponse
import com.chlqudco.develop.thinkit.data.response.MultipleChoiceQuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConceptApiService {

    @GET("/api/keywords/{subject}")
    suspend fun getKeywords(
        @Path("subject") subject: String
    ) : Response<KeywordsResponse>

    @GET("/api/concepts")
    suspend fun getContent(
        @Query("keyword") keyword: String
    ): Response<ContentResponse>

}