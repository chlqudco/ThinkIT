package com.chlqudco.develop.thinkit.domain.concept

import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import com.chlqudco.develop.thinkit.data.repository.ConceptRepository

internal class InsertKeywordUseCase(
    private val conceptRepository: ConceptRepository
) {

    suspend operator fun invoke(keyword: KeywordsEntity){
        conceptRepository.insertKeyword(keyword)
    }
}