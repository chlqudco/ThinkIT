package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.model.Chat
import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.domain.Usecase

class GetChatListUseCase(
    private val myPageRepository: MyPageRepository
) : Usecase {

    suspend operator fun invoke(userToken: String): List<Chat>?{
        return null
    }
}