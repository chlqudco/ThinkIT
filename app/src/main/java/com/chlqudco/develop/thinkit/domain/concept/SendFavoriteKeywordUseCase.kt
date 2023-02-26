package com.chlqudco.develop.thinkit.domain.concept

import com.chlqudco.develop.thinkit.data.repository.ConceptRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class SendFavoriteKeywordUseCase(
    private val conceptRepository: ConceptRepository
): Usecase {

    suspend operator fun invoke(keyword: String, token: String, isClicked: Boolean): String{
        return conceptRepository.sendFavoriteKeyword(keyword, token, isClicked)
    }
}