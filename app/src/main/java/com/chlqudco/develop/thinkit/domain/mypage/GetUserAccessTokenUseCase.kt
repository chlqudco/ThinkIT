package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.domain.Usecase

class GetUserAccessTokenUseCase(
    private val myPageRepository: MyPageRepository
) : Usecase {

    suspend operator fun invoke(): String{
        return myPageRepository.getUserAccessToken()
    }
}