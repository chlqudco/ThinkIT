package com.chlqudco.develop.thinkit.domain.mypage

import com.chlqudco.develop.thinkit.data.repository.MyPageRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class SaveTokenAndNickNameUseCase(
    private val myPageRepository: MyPageRepository
): Usecase {
    suspend operator fun invoke(token: String, nickname: String) {
        myPageRepository.saveTokenAndNickName(token, nickname)
    }
}