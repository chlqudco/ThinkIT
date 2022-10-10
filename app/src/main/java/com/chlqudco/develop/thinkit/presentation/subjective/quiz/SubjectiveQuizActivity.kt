package com.chlqudco.develop.thinkit.presentation.subjective.quiz

import android.content.Intent
import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.subjective.result.SubjectiveResultActivity
import org.koin.android.ext.android.inject

internal class SubjectiveQuizActivity : BaseActivity<SubjectiveQuizViewModel, ActivitySubjectiveQuizBinding>() {

    private var quizCount : Int = 1

    override val viewModel: SubjectiveQuizViewModel by inject()

    override fun getViewBinding() = ActivitySubjectiveQuizBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {

        binding.SubjectiveQuizNextButton.setOnClickListener {
            // 마지막 문제인 경우
            if (quizCount == 10){
                val intent = Intent(this, SubjectiveResultActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                quizCount++
                binding.SubjectiveQuizTitleTextView.text = "${quizCount}번째"
                if(quizCount==10){
                    binding.SubjectiveQuizNextButton.text = "퀴즈 종료"
                }
            }
        }

    }
}