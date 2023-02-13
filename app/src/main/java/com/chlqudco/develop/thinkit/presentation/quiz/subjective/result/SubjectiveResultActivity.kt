package com.chlqudco.develop.thinkit.presentation.quiz.subjective.result

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveResultBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz.ShowIncorrectQuizActivity
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.SUBJECTIVE_QUIZ_TIME
import org.koin.android.ext.android.inject

internal class SubjectiveResultActivity : BaseActivity<SubjectiveResultViewModel, ActivitySubjectiveResultBinding>() {

    override val viewModel: SubjectiveResultViewModel by inject()

    override fun getViewBinding() = ActivitySubjectiveResultBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        //틀린것들 불러오기
        viewModel.inCorrectQuizList = intent.getStringArrayListExtra(AppKey.MULTIPLE_INCORRECT_QUIZ) ?: arrayListOf()
        viewModel.inCorrectBogiList = intent.getStringArrayListExtra(AppKey.MULTIPLE_INCORRECT_BOGI) ?: arrayListOf()

        val delayTime = intent.getLongExtra(SUBJECTIVE_QUIZ_TIME, 0)

        val minute = delayTime/1000/60
        val seconds = delayTime/1000%60

        binding.ActivitySubjectiveResultTimeTextView.text = "${minute}분 ${seconds}초"

        //나가기 버튼
        binding.ActivitySubjectiveResultExitButton.setOnClickListener {
            finish()
        }

        //문제 다시보기 기능
        binding.ActivitySubjectiveResultShowQuizButton.setOnClickListener {
            val intent = Intent(this, ShowIncorrectQuizActivity::class.java)

            //정보 넘겨주기
            //틀린 문제
            intent.putStringArrayListExtra(AppKey.MULTIPLE_INCORRECT_QUIZ, viewModel.inCorrectQuizList)
            //틀린 보기
            intent.putStringArrayListExtra(AppKey.MULTIPLE_INCORRECT_BOGI, viewModel.inCorrectBogiList)

            startActivity(intent)
        }
    }
}