package com.chlqudco.develop.thinkit.presentation.explanationwebview

import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationState

internal sealed class ExplanationWebViewState {
    object UnInitialized: ExplanationWebViewState()

    object Loading: ExplanationWebViewState()

    data class Success(
        val content: String
    ): ExplanationWebViewState()

    object Error: ExplanationWebViewState()
}