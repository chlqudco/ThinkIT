package com.chlqudco.develop.thinkit.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.chlqudco.develop.thinkit.domain.mypage.*
import com.chlqudco.develop.thinkit.domain.mypage.GetLogInTokenUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetUserNickNameUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetUserTokenUseCase
import com.chlqudco.develop.thinkit.domain.mypage.InitUserInfoUseCase

internal class MyPageViewModel(
    private val getLogInTokenUseCase: GetLogInTokenUseCase,
    private val getUserTokenUseCase : GetUserTokenUseCase,
    private val getUserNickNameUseCase: GetUserNickNameUseCase,
    private val initUserInfoUseCase: InitUserInfoUseCase,
    private val saveTokenAndNickNameUseCase: SaveTokenAndNickNameUseCase
) : BaseViewModel() {

    private val _logInStateLiveData = MutableLiveData<MyPageState>(MyPageState.UnInitialized)
    val logInStateLiveData: LiveData<MyPageState> = _logInStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _logInStateLiveData.postValue(MyPageState.UnInitialized)
        _logInStateLiveData.postValue(MyPageState.Loading)
    }

    suspend fun checkLogIn(): Boolean {
        val token = getUserToken()
        return token.isNotEmpty()
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
                _logInStateLiveData.postValue(
                    MyPageState.Success(
                        token = response.token,
                        nickName = response.nickname
                    )
                )

                //DataStore 에 토큰과 닉네임 저장
                saveTokenAndNickNameUseCase(response.token, response.nickname)
            }
        }
    }

    suspend fun logOut() {
        // DataStore 초기화
        initUserInfoUseCase()
    }

    // DataStore에서 토큰 가져오기
    suspend fun getUserToken(): String {
        return getUserTokenUseCase()
    }

    // DataStore에서 닉네임 가져오기
    suspend fun getNickName(): String {
        return getUserNickNameUseCase()
    }

}