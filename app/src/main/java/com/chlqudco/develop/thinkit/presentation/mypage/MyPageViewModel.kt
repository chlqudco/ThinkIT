package com.chlqudco.develop.thinkit.presentation.mypage

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.mypage.GetLogInTokenUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import com.chlqudco.develop.thinkit.utility.AppKey.USER_NICK_NAME
import com.chlqudco.develop.thinkit.utility.AppKey.USER_TOKEN
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MyPageViewModel(
    private val sharedPreferences: SharedPreferences,
    private val getLogInTokenUseCase: GetLogInTokenUseCase
) : BaseViewModel() {

    private val _logInStateLiveData = MutableLiveData<MyPageState>(MyPageState.UnInitialized)
    val logInStateLiveData: LiveData<MyPageState> = _logInStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _logInStateLiveData.postValue(MyPageState.UnInitialized)
        _logInStateLiveData.postValue(MyPageState.Loading)
    }

    fun checkLogIn(): Boolean {
        val token = sharedPreferences.getString(USER_TOKEN, "") ?: ""
        return token.isNotEmpty()
    }

    fun getUserNickName(): String {
        return sharedPreferences.getString(USER_NICK_NAME, "") ?: ""
    }

    fun logIn(userName: String, password: String) {

        viewModelScope.launch {
            //로그인 한 뒤 토큰 받아오기
            val response = getLogInTokenUseCase(userName, password)
            //실패한 경우
            if (response == null || response.result == "fail") {
                _logInStateLiveData.postValue(MyPageState.Error)
            } else {
                //성공한 경우
                _logInStateLiveData.postValue(MyPageState.Success(token = response.token, nickName = response.nickname))

                //셰프에 토큰과 닉네임 저장
                sharedPreferences.edit {
                    putString(USER_TOKEN, response.token)
                    putString(USER_NICK_NAME, response.nickname)
                }
            }
        }
    }

    fun logOut() {
        //셰프 비우기
        sharedPreferences.edit {
            putString(USER_TOKEN, "")
            putString(USER_NICK_NAME, "")
        }
    }
}