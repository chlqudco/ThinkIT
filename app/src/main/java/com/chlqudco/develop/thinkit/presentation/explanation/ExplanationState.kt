package com.chlqudco.develop.thinkit.presentation.explanation

import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsState

internal sealed class ExplanationState {

    object UnInitialized: ExplanationState()

    object Loading: ExplanationState()

    data class Success(
        val content: String
    ): ExplanationState()

    object Error: ExplanationState()
}