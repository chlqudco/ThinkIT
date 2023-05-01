package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.domain.Usecase

class GetChatRoomIdDataStoreUseCase(
    val myPageRepository: MyPageRepository
): Usecase {

    suspend operator fun invoke(): Long{
        return myPageRepository.getChatRoomIdToDataStore()
    }
}