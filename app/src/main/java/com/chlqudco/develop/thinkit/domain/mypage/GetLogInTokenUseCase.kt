package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.data.response.LogInResponse
import com.chlqudco.develop.thinkit.domain.Usecase

internal class GetLogInTokenUseCase(
    private val myPageRepository: MyPageRepository
): Usecase {

    suspend operator fun invoke(userName: String, password: String): LogInResponse?{
        return myPageRepository.requestLogIn(userName, password)
    }

}