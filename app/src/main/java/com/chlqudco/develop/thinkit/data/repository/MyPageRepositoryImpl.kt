package com.chlqudco.develop.thinkit.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.chlqudco.develop.thinkit.data.entity.LogInEntity
import com.chlqudco.develop.thinkit.data.entity.SignUpEntity
import com.chlqudco.develop.thinkit.data.network.MyPageApiService
import com.chlqudco.develop.thinkit.data.response.LogInResponse
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.DATASTORE_USER_NICKNAME
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

                Log.v("logIn", "555555555")
                if (response.isSuccessful) {
                    Log.v("logIn", "6666666666666")
                    return@withContext response.body()
                } else {
                    Log.v("logIn", "77777777777")
                    Log.v("logIn", response.errorBody().toString())
                    return@withContext null
                }
            } catch (exception: Exception) {
                Log.v("logIn", exception.toString())
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

    override suspend fun getUserToken(): String {
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
            prefs[AppKey.DATASTORE_USER_TOKEN] = ""
            prefs[DATASTORE_USER_NICKNAME] = ""
        }
    }

    override suspend fun saveTokenAndNickName(token: String, nickname: String) {
        dataStore.edit { prefs ->
            prefs[AppKey.DATASTORE_USER_TOKEN] = token
            prefs[DATASTORE_USER_NICKNAME] = nickname
        }
    }
}