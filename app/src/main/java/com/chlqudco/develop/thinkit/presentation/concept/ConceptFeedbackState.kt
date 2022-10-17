package com.chlqudco.develop.thinkit.presentation.concept

import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizState

internal sealed class ConceptFeedbackState {
    object UnInitialized: ConceptFeedbackState()

    object Loading: ConceptFeedbackState()

    data class Success(
        val feedbackResult: Boolean
    ): ConceptFeedbackState()

    object Error: ConceptFeedbackState()
}