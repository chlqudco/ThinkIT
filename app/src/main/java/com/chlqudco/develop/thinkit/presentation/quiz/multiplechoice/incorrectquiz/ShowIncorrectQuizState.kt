package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz

internal sealed class ShowIncorrectQuizState {
    object UnInitialized: ShowIncorrectQuizState()

    object Loading: ShowIncorrectQuizState()

    data class Success(
        val inCorrectList: List<String>
    ): ShowIncorrectQuizState()

    object Error: ShowIncorrectQuizState()
}