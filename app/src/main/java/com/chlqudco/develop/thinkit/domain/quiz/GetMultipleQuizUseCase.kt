package com.chlqudco.develop.thinkit.domain.quiz

import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity
import com.chlqudco.develop.thinkit.data.repository.QuizRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class GetMultipleQuizUseCase(
    private val quizRepository: QuizRepository
): Usecase {

    suspend operator fun invoke(subjects: List<String>): List<MultipleChoiceEntity> {
        return quizRepository.getMultipleChoiceQuizList(subjects)
    }
}