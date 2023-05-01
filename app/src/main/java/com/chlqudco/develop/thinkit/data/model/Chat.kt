package com.chlqudco.develop.thinkit.data.model

data class Chat(
    val id : Long,
    val myChat : Boolean,
    val message: String,
    val createdAt : Long
) {
}