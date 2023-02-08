package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.core.view.isVisible
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.result.SubjectiveResultActivity
import com.chlqudco.develop.thinkit.utility.AppKey.QUIZ_SUBJECT_LIST
import com.chlqudco.develop.thinkit.utility.AppKey.SUBJECTIVE_QUIZ_TIME
import org.koin.android.ext.android.inject

internal class SubjectiveQuizActivity : BaseActivity<SubjectiveQuizViewModel, ActivitySubjectiveQuizBinding>() {

    private var startTime: Long = 0L
    private var quizCount : Int = 1

    override val viewModel: SubjectiveQuizViewModel by inject()

    override fun getViewBinding() = ActivitySubjectiveQuizBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.subjectiveQuizLiveData.observe(this){
            when(it){
                is SubjectiveQuizState.UnInitialized -> {
                    initViews()
                }
                is SubjectiveQuizState.Loading -> {
                    requestQuizList()
                }
                is SubjectiveQuizState.Success -> {
                    handleSuccessState(it)
                }
                is SubjectiveQuizState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleErrorState() {
        showToastMessage("오류가 발생했습니다")
        finish()
    }

    private fun handleSuccessState(state: SubjectiveQuizState.Success) {
        //그만 돌아
        binding.ActivitySubjectiveQuizProgressBar.isVisible = false
        binding.ActivitySubjectiveQuizGroup.isVisible = true

        binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = false

        //시작 시간 측정
        startTime = System.currentTimeMillis()

        //첫번째 문제 세팅하기
        setQuizByIndex(0)
    }

    private fun setQuizByIndex(index: Int) {
        val quiz = viewModel.getQuizText(index).split("@")[0]
        val answer1 = viewModel.getQuizText(index).split("@")[1]
        val answer2 = answer1.split(".")

        var answer: String = ""
        for (item in answer2){
            answer += (" $item.\n\n")
        }

        binding.ActivitySubjectiveQuizQuizTextView.text = quiz
        binding.ActivitySubjectiveQuizBestAnswerTextView.text = answer.dropLast(3)

    }

    private fun requestQuizList() {
        //문제 받아오기
        val subjects = intent.getStringArrayListExtra(QUIZ_SUBJECT_LIST) ?: listOf<String>()
        viewModel.getSubjectiveQuizList(subjects.toList())
    }

    private fun initViews() {

        // 다음 문제 버튼
        binding.ActivitySubjectiveQuizNextButton.setOnClickListener {
            // 마지막 문제인 경우
            if (quizCount == 10){
                goResultActivity()
            } else{
                showNextQuiz()
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

    @SuppressLint("SetTextI18n")
    private fun showNextQuiz() {
        //퀴즈 갱신
        setQuizByIndex(quizCount)

        quizCount++
        binding.ActivitySubjectiveQuizTopTextView.text = "${quizCount}번째"
        if(quizCount==10){
            binding.ActivitySubjectiveQuizNextButton.text = "퀴즈 종료"
        }
    }

    private fun goResultActivity() {
        val intent = Intent(this, SubjectiveResultActivity::class.java)
        intent.putExtra(SUBJECTIVE_QUIZ_TIME, System.currentTimeMillis() - startTime)
        startActivity(intent)
        finish()
    }
}