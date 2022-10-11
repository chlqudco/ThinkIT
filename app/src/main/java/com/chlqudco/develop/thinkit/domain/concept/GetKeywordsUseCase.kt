package com.chlqudco.develop.thinkit.domain.concept

import com.chlqudco.develop.thinkit.data.repository.ConceptRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class GetKeywordsUseCase(
    private val conceptRepository: ConceptRepository
): Usecase {

    suspend operator fun invoke(subject: String): List<String>{
        return conceptRepository.getKeywordsList(subject)
    }
}