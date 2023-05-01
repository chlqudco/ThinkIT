package com.chlqudco.develop.thinkit.data.response

import com.chlqudco.develop.thinkit.data.model.Chat

data class ChatResponse(
    val isSuccess : Boolean,
    val chatList : List<Chat>
)