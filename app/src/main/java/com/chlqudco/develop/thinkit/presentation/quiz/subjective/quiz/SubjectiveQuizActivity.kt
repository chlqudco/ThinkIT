package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.result.SubjectiveResultActivity
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

        binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = false

        // 다음 문제 버튼
        binding.ActivitySubjectiveQuizNextButton.setOnClickListener {
            // 마지막 문제인 경우
            if (quizCount == 10){
                val intent = Intent(this, SubjectiveResultActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                quizCount++
                binding.ActivitySubjectiveQuizTopTextView.text = "${quizCount}번째"
                if(quizCount==10){
                    binding.ActivitySubjectiveQuizNextButton.text = "퀴즈 종료"
                }
            }

            //답 보는거 초기화
            binding.ActivitySubjectiveQuizShowBestAnswerButton.isVisible = true
            binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = false
        }

        //정답 보기 버튼
        binding.ActivitySubjectiveQuizShowBestAnswerButton.setOnClickListener {
            binding.ActivitySubjectiveQuizShowBestAnswerButton.isVisible = false
            binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = true
        }

    }
}