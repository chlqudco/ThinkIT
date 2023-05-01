package com.chlqudco.develop.thinkit.presentation.keywords

sealed class KeywordsState {

    object UnInitialized: KeywordsState()

    object Loading: KeywordsState()

    data class Success(
        val keywordsList: List<String>
    ): KeywordsState()

    object Error: KeywordsState()
}