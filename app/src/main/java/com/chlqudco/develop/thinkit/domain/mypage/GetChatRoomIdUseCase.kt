package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.data.response.ChatRoomIdResponse
import com.chlqudco.develop.thinkit.domain.Usecase

class GetChatRoomIdUseCase(
    private val myPageRepository: MyPageRepository
) : Usecase {

    suspend operator fun invoke(accessToken: String, refreshToken: String): Long {
        return myPageRepository.getChatRoomId(accessToken, refreshToken)
    }
}