package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivityMultipleChoiceResultBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_QUIZ_SCORE
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_QUIZ_TIME
import org.koin.android.ext.android.inject

internal class MultipleChoiceResultActivity : BaseActivity<MultipleChoiceResultViewModel, ActivityMultipleChoiceResultBinding>() {


    override val viewModel: MultipleChoiceResultViewModel by inject()

    override fun getViewBinding() = ActivityMultipleChoiceResultBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        val delayTime = intent.getLongExtra(MULTIPLE_QUIZ_TIME, -1)
        val userScore = intent.getIntExtra(MULTIPLE_QUIZ_SCORE, -1)

        val minute = delayTime/1000/60
        val seconds = delayTime/1000

        binding.ActivityMultipleChoiceResultCorrectCountTextView.text = " ${userScore/10} / 10 개"
        binding.ActivityMultipleChoiceResultTimeTextView.text = "${minute}분 ${seconds}초"
    }

    private fun initViews() {
        binding.ActivityMultipleChoiceResultExitButton.setOnClickListener {
            finish()
        }
    }

}