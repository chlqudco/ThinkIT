package com.chlqudco.develop.thinkit.presentation.concept

internal sealed class ConceptFeedbackState {
    object UnInitialized: ConceptFeedbackState()

    object Loading: ConceptFeedbackState()

    data class Success(
        val feedbackResult: Boolean
    ): ConceptFeedbackState()

    object Error: ConceptFeedbackState()
}