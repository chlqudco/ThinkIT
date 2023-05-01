package com.chlqudco.develop.thinkit.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.mypage.GetChatRoomIdDataStoreUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetChatRoomIdUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetUserAccessTokenUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetUserRefreshTokenUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel(
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase,
    private val getUserRefreshTokenUseCase: GetUserRefreshTokenUseCase,
    private val getChatRoomIdUseCase: GetChatRoomIdUseCase,
    private val getChatRoomIdDataStoreUseCase: GetChatRoomIdDataStoreUseCase
): BaseViewModel() {

    private var _chatRoomIdStateLiveData = MutableLiveData<ChatState>(ChatState.UnInitialized)
    val chatRoomIdStateLiveData: LiveData<ChatState> = _chatRoomIdStateLiveData

    private val _chatRoomIdDataStoreLiveData = MutableLiveData<Long>()
    val chatRoomIdDataStoreLiveData: LiveData<Long> = _chatRoomIdDataStoreLiveData

    lateinit var userRefreshToken: String
    lateinit var userAccessToken: String

    override fun fetchData(): Job = viewModelScope.launch {

        _chatRoomIdStateLiveData.postValue(ChatState.UnInitialized)

        // 사용자 토큰 받아오기
        userRefreshToken = getUserRefreshTokenUseCase()
        userAccessToken = getUserAccessTokenUseCase()
    }

    fun getChatRoomId() = viewModelScope.launch{
        // RoomId 가져오기
        _chatRoomIdStateLiveData.postValue(ChatState.Loading)

        val response = getChatRoomIdUseCase(userAccessToken, userRefreshToken)

        if (response == -1L){
            _chatRoomIdStateLiveData.postValue(ChatState.Error)
        } else{
            _chatRoomIdStateLiveData.postValue(ChatState.Success(response))
        }
    }

    fun getChatRoomIdToDataStore(){
        viewModelScope.launch {
            _chatRoomIdDataStoreLiveData.postValue(99)
            delay(100)
            _chatRoomIdDataStoreLiveData.postValue(getChatRoomIdDataStoreUseCase() ?: -1)
        }
    }

}