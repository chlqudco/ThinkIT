package com.chlqudco.develop.thinkit.domain.feedback

import com.chlqudco.develop.thinkit.data.repository.FeedbackRepository
import com.chlqudco.develop.thinkit.domain.Usecase

internal class PostFeedbackUseCase(
    private val feedbackRepository: FeedbackRepository
): Usecase {

    suspend operator fun invoke(feedback: String): Boolean{
        return feedbackRepository.sendFeedback(feedback)
    }

}