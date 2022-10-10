package com.chlqudco.develop.thinkit.presentation.multiplechoice.quiz

import android.content.Intent
import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivityMultipleChoiceQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.multiplechoice.result.MultipleChoiceResultActivity
import org.koin.android.ext.android.inject

internal class MultipleChoiceQuizActivity : BaseActivity<MultipleChoiceQuizViewModel, ActivityMultipleChoiceQuizBinding>() {

    private var quizCount : Int = 1

    override val viewModel: MultipleChoiceQuizViewModel by inject()

    override fun getViewBinding() = ActivityMultipleChoiceQuizBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.MultipleChoiceNextButton.setOnClickListener {
            // 마지막 문제인 경우
            if (quizCount == 10){
                val intent = Intent(this, MultipleChoiceResultActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                quizCount++
                binding.MultipleChoiceTitleTextView.text = "${quizCount}번째"
                if(quizCount==10){
                    binding.MultipleChoiceNextButton.text = "퀴즈 종료"
                }
            }
        }
    }
}