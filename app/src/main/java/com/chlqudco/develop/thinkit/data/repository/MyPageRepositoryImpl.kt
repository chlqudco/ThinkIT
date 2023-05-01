package com.chlqudco.develop.thinkit.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.chlqudco.develop.thinkit.data.entity.LogInEntity
import com.chlqudco.develop.thinkit.data.entity.SignUpEntity
import com.chlqudco.develop.thinkit.data.model.Chat
import com.chlqudco.develop.thinkit.data.network.MyPageApiService
import com.chlqudco.develop.thinkit.data.response.ChatRoomIdResponse
import com.chlqudco.develop.thinkit.data.response.LogInResponse
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.DATASTORE_USER_ACCESS_TOKEN
import com.chlqudco.develop.thinkit.utility.AppKey.DATASTORE_USER_CHAT_ROOM_ID
import com.chlqudco.develop.thinkit.utility.AppKey.DATASTORE_USER_NICKNAME
import com.chlqudco.develop.thinkit.utility.AppKey.DATASTORE_USER_REFRESH_TOKEN
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

class MyPageRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val myPageApiService: MyPageApiService,
    private val ioDispatcher: CoroutineDispatcher
) : MyPageRepository {

    override suspend fun requestLogIn(userName: String, password: String): LogInResponse? =
        withContext(ioDispatcher) {
            try {
                val logInEntity = LogInEntity(userName, password)
                val response = myPageApiService.logIn2(logInEntity)

                if (response.isSuccessful) {
                    // accessToken 꺼내기
                    val accessToken = response.headers()["accessToken"].toString()

                    // accessToken 저장하기
                    saveAccessToken(accessToken)

                    return@withContext response.body()
                } else {
                    return@withContext null
                }
            } catch (exception: Exception) {
                return@withContext null
            }
        }

    override suspend fun requestSignUp(
        userName: String,
        password: String,
        nickName: String
    ): String = withContext(ioDispatcher) {
        try {
            val signUpEntity = SignUpEntity(userName, password, nickName)
            val response = myPageApiService.signUp(signUpEntity)
            if (response.isSuccessful) {
                return@withContext response.body()?.result ?: "fail"
            } else {
                return@withContext "fail"
            }
        } catch (e: Exception) {
            return@withContext "fail"
        }
    }

    override suspend fun getUserAccessToken(): String {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[DATASTORE_USER_ACCESS_TOKEN] ?: ""
            }.first()
    }

    override suspend fun getUserRefreshToken(): String {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[DATASTORE_USER_REFRESH_TOKEN] ?: ""
            }.first()
    }

    override suspend fun getUserNickName(): String {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[DATASTORE_USER_NICKNAME] ?: ""
            }.first()
    }

    override suspend fun initUserInfo() {
        dataStore.edit { prefs ->
            prefs[DATASTORE_USER_ACCESS_TOKEN] = ""
            prefs[DATASTORE_USER_REFRESH_TOKEN] = ""
            prefs[DATASTORE_USER_NICKNAME] = ""
        }
    }

    override suspend fun saveRefreshTokenAndNickName(token: String, nickname: String) {
        dataStore.edit { prefs ->
            prefs[DATASTORE_USER_REFRESH_TOKEN] = token
            prefs[DATASTORE_USER_NICKNAME] = nickname
        }
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { prefs ->
            prefs[DATASTORE_USER_ACCESS_TOKEN] = token
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit { prefs ->
            prefs[DATASTORE_USER_REFRESH_TOKEN] = token
        }
    }

    override suspend fun getChatRoomId(accessToken: String, refreshToken: String): Long = withContext(ioDispatcher){
        try {
            val response = myPageApiService.getRoomId(accessToken, refreshToken)
            if (response.isSuccessful && (response.body()?.isSuccess == true)){
                val newAccessToken = response.headers()["accessToken"]
                if (newAccessToken != null) {
                    saveAccessToken(newAccessToken)
                }
                saveRefreshToken(response.body()!!.refreshToken)
                saveChatRoomId(response.body()!!.roomId)
                return@withContext response.body()!!.roomId
            } else{
                return@withContext -1
            }
        } catch (e: Exception){
            return@withContext -1
        }
    }

    override suspend fun getChatRoomIdToDataStore(): Long {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[DATASTORE_USER_CHAT_ROOM_ID] ?: -1
            }.first()
    }

    override suspend fun saveChatRoomId(id: Long) {
        dataStore.edit { prefs ->
            prefs[DATASTORE_USER_CHAT_ROOM_ID] = id
        }
    }

}