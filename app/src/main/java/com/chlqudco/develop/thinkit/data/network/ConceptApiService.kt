package com.chlqudco.develop.thinkit.data.network

import com.chlqudco.develop.thinkit.data.entity.KeywordFavoriteEntity
import com.chlqudco.develop.thinkit.data.response.ContentResponse
import com.chlqudco.develop.thinkit.data.response.KeywordFavoriteResponse
import com.chlqudco.develop.thinkit.data.response.KeywordsResponse
import com.chlqudco.develop.thinkit.data.response.MultipleChoiceQuizResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ConceptApiService {

    @GET("/api/keywords/{subjectName}")
    suspend fun getKeywords(
        @Path("subjectName") subjectName: String
    ) : Response<KeywordsResponse>

    @GET("/view/concepts/{keywordName}")
    suspend fun getContent(
        @Path("keywordName") keywordName: String
    ): Response<ContentResponse>

    @POST("auth/favorites/keywords/{keyword}")
    suspend fun favoriteKeyword(
        @Body keywordFavoriteEntity: KeywordFavoriteEntity
    ): Response<KeywordFavoriteResponse>

    // Flow 적용 연습
    @GET("/api/keywords/{subjectName}")
    suspend fun getKeywordsToFlow(
        @Path("subjectName") subjectName: String
    ) : Flow<Response<KeywordsResponse>>

}