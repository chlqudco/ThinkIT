package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class SignUpUseCase(
    private val myPageRepository: MyPageRepository
): Usecase {

    suspend operator fun invoke(userName: String, password: String, nickName: String): String{
        return myPageRepository.requestSignUp(userName, password, nickName)
    }
}