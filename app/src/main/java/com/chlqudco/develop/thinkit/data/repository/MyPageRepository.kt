package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.model.Chat
import com.chlqudco.develop.thinkit.data.response.ChatRoomIdResponse
import com.chlqudco.develop.thinkit.data.response.LogInResponse

interface MyPageRepository {

    suspend fun requestLogIn(userName: String, password: String): LogInResponse?

    suspend fun requestSignUp(userName: String, password: String, nickName: String): String

    suspend fun getUserAccessToken(): String

    suspend fun getUserRefreshToken(): String

    suspend fun getUserNickName(): String

    suspend fun initUserInfo()

    suspend fun saveAccessToken(token: String)

    suspend fun saveRefreshToken(token: String)

    suspend fun saveRefreshTokenAndNickName(token: String, nickname: String)

    suspend fun getChatRoomId(accessToken: String, refreshToken: String): Long

    suspend fun saveChatRoomId(id: Long)

    suspend fun getChatRoomIdToDataStore(): Long
}