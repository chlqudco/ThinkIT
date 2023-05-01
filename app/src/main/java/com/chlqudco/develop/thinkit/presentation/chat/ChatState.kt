package com.chlqudco.develop.thinkit.presentation.chat

sealed class ChatState {

    object UnInitialized: ChatState()

    object Loading: ChatState()

    data class Success(
        val roomId: Long
    ): ChatState()

    object Error: ChatState()
}