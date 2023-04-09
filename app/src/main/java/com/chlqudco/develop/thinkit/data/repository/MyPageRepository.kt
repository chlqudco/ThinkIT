package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.response.LogInResponse

interface MyPageRepository {

    suspend fun requestLogIn(userName: String, password: String): LogInResponse?

    suspend fun requestSignUp(userName: String, password: String, nickName: String): String

    suspend fun getUserToken(): String

    suspend fun getUserNickName(): String

    suspend fun initUserInfo()

    suspend fun saveTokenAndNickName(token: String, nickname: String)
}