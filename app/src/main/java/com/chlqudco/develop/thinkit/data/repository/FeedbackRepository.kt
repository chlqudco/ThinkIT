package com.chlqudco.develop.thinkit.data.repository

interface FeedbackRepository {

    suspend fun sendFeedback(feedback: String): Boolean
}