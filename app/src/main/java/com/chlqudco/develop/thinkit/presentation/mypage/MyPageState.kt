package com.chlqudco.develop.thinkit.presentation.mypage

internal sealed class MyPageState {

    object UnInitialized: MyPageState()

    object Loading: MyPageState()

    data class Success(
        val token: String,
        val nickName: String
    ): MyPageState()

    object Error: MyPageState()

}