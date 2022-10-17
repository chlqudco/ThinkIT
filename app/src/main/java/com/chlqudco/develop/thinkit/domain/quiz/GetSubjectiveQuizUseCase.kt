package com.chlqudco.develop.thinkit.domain.quiz

import com.chlqudco.develop.thinkit.data.repository.QuizRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class GetSubjectiveQuizUseCase(
    private val quizRepository: QuizRepository
): Usecase {
    suspend operator fun invoke(subjects: List<String>): List<String>{
        return quizRepository.getSubjectiveQuizList(subjects)
    }
}