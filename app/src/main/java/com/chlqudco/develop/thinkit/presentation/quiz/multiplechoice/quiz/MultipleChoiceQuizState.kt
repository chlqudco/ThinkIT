package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz

import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity

internal sealed class MultipleChoiceQuizState {
    object UnInitialized: MultipleChoiceQuizState()

    object Loading: MultipleChoiceQuizState()

    data class Success(
        val quizList: List<MultipleChoiceEntity>
    ): MultipleChoiceQuizState()

    object Error: MultipleChoiceQuizState()
}