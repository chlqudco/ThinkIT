package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

internal sealed class SubjectiveQuizState {
    object UnInitialized: SubjectiveQuizState()

    object Loading: SubjectiveQuizState()

    data class Success(
        val quizList: List<String>
    ): SubjectiveQuizState()

    object Error: SubjectiveQuizState()
}