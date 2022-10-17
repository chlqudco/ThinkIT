package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz

import com.chlqudco.develop.thinkit.databinding.ActivityShowIncorrectQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

internal class ShowIncorrectQuizActivity : BaseActivity<ShowIncorrectQuizViewModel, ActivityShowIncorrectQuizBinding>() {
    override val viewModel: ShowIncorrectQuizViewModel by inject()

    override fun getViewBinding() = ActivityShowIncorrectQuizBinding.inflate(layoutInflater)

    override fun observeData() {

    }

}