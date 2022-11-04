package com.chlqudco.develop.thinkit.domain.concept

import com.chlqudco.develop.thinkit.data.repository.ConceptRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class DeleteKeywordDBUseCase(
    private val conceptRepository: ConceptRepository
) : Usecase{

    suspend operator fun invoke(concept: String){
        conceptRepository.deleteKeywords(concept)
    }
}