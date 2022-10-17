package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivityMultipleChoiceResultBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz.ShowIncorrectQuizActivity
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_INCORRECT_BOGI
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_INCORRECT_QUIZ
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
        //틀린것들 불러오기
        viewModel.inCorrectQuizList = intent.getStringArrayListExtra(MULTIPLE_INCORRECT_QUIZ) ?: arrayListOf()
        viewModel.inCorrectBogiList = intent.getStringArrayListExtra(MULTIPLE_INCORRECT_BOGI) ?: arrayListOf()

        //닫기 버튼
        binding.ActivityMultipleChoiceResultExitButton.setOnClickListener {
            finish()
        }

        //틀린거 보러가기
        binding.ActivityMultipleChoiceResultShowIncorrectQuizButton.setOnClickListener {
            val intent = Intent(this, ShowIncorrectQuizActivity::class.java)

            //정보 넘겨주기
            //틀린 문제
            intent.putStringArrayListExtra(MULTIPLE_INCORRECT_QUIZ, viewModel.inCorrectQuizList)
            //틀린 보기
            intent.putStringArrayListExtra(MULTIPLE_INCORRECT_BOGI, viewModel.inCorrectBogiList)

            startActivity(intent)
        }
    }

}