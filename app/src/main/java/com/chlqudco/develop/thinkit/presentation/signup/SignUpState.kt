package com.chlqudco.develop.thinkit.presentation.signup

internal sealed class SignUpState {

    object UnInitialized: SignUpState()

    object Loading: SignUpState()

    data class Success(
        val result: String
    ): SignUpState()

    object Error: SignUpState()
}