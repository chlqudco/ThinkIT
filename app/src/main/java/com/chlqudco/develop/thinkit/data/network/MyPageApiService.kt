package com.chlqudco.develop.thinkit.data.network

import com.chlqudco.develop.thinkit.data.entity.LogInEntity
import com.chlqudco.develop.thinkit.data.entity.SignUpEntity
import com.chlqudco.develop.thinkit.data.response.ChatResponse
import com.chlqudco.develop.thinkit.data.response.ChatRoomIdResponse
import com.chlqudco.develop.thinkit.data.response.LogInResponse
import com.chlqudco.develop.thinkit.data.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.*

interface MyPageApiService {

    @POST("/login")
    suspend fun logIn2(
        @Body logInEntity: LogInEntity
    ): Response<LogInResponse>

    @POST("/join")
    suspend fun signUp(
        @Body signUpEntity: SignUpEntity
    ): Response<SignUpResponse>

    @POST("/chat")
    suspend fun getRoomId(
        @Header("accessToken") accessToken: String,
        @Query("refreshToken") refreshToken : String
    ): Response<ChatRoomIdResponse>
}