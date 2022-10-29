package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.core.view.isVisible
import com.chlqudco.develop.thinkit.databinding.ActivityMultipleChoiceQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result.MultipleChoiceResultActivity
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_INCORRECT_BOGI
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_INCORRECT_QUIZ
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_QUIZ_SCORE
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_QUIZ_TIME
import com.chlqudco.develop.thinkit.utility.AppKey.QUIZ_SUBJECT_LIST
import org.koin.android.ext.android.inject

internal class MultipleChoiceQuizActivity : BaseActivity<MultipleChoiceQuizViewModel, ActivityMultipleChoiceQuizBinding>() {

    private var startTime: Long = 0L
    private var quizCount : Int = 1

    //현재 정답의 답 텍스트 저장
    private var originAnswer = ""

    //제출 버튼 눌렀는지 체크
    private var isSendButtonChecked = false

    override val viewModel: MultipleChoiceQuizViewModel by inject()

    override fun getViewBinding() = ActivityMultipleChoiceQuizBinding.inflate(layoutInflater)

    private fun initViews() {

        //다음 문제 버튼 클릭 리스너
        binding.ActivityMultipleChoiceQuizNextButton.setOnClickListener {

            //아직 제출 안한 경우
            if (!isSendButtonChecked){
                showToastMessage("아직 제출하지 않았습니다")
                return@setOnClickListener
            }

            isSendButtonChecked = false

            // 마지막 문제인 경우 결과 화면으로 이동
            if (quizCount == 10){
                goResultActivity()
            } else{
                showNextQuiz()
            }
        }

        //제출 버튼 클릭 리스너
        binding.ActivityMultipleChoiceQuizCheckCorrectButton.setOnClickListener {

            //아무것도 안 누른 경우
            if (binding.ActivityMultipleChoiceQuizRadioGroup.checkedRadioButtonId == -1){
                showToastMessage("보기를 골라주세요")
                return@setOnClickListener
            }

            //이미 제출한 경우
            if (isSendButtonChecked){
                showToastMessage("한 번만 제출할 수 있습니다")
                return@setOnClickListener
            }

            //정답 판별
            if (checkCorrectAnswer()){
                showToastMessage("정답입니다")
                viewModel.totalScore += 10
            } else{
                showToastMessage("오답입니다")
                //뷰모델에 틀린 문제 넘기기
                viewModel.addInCorrectQuiz(binding.ActivityMultipleChoiceQuizTextTextView.text.toString(), originAnswer)
            }

            isSendButtonChecked = true
        }
    }

    private fun checkCorrectAnswer(): Boolean {
        if (binding.ActivityMultipleChoiceQuizSelect1RadioButton.isChecked && binding.ActivityMultipleChoiceQuizSelect1RadioButton.text == originAnswer){
            return true
        } else if (binding.ActivityMultipleChoiceQuizSelect2RadioButton.isChecked && binding.ActivityMultipleChoiceQuizSelect2RadioButton.text == originAnswer){
            return true
        } else if (binding.ActivityMultipleChoiceQuizSelect3RadioButton.isChecked && binding.ActivityMultipleChoiceQuizSelect3RadioButton.text == originAnswer){
            return true
        } else if (binding.ActivityMultipleChoiceQuizSelect4RadioButton.isChecked && binding.ActivityMultipleChoiceQuizSelect4RadioButton.text == originAnswer){
            return true
        }
        return false
    }

    @SuppressLint("SetTextI18n")
    private fun showNextQuiz() {
        //일단 퀴즈 갱신하고
        setQuizByIndex(quizCount)

        //나머지 갱신
        quizCount++
        binding.ActivityMultipleChoiceQuizNumberTextView.text = "${quizCount}번째"
        if(quizCount==10){
            binding.ActivityMultipleChoiceQuizNextButton.text = "퀴즈 종료"
        }
    }

    private fun goResultActivity() {
        val intent = Intent(this, MultipleChoiceResultActivity::class.java)
        //걸린 시간
        intent.putExtra(MULTIPLE_QUIZ_TIME,System.currentTimeMillis() - startTime)
        //총 점수
        intent.putExtra(MULTIPLE_QUIZ_SCORE, viewModel.totalScore)
        //틀린 문제
        intent.putStringArrayListExtra(MULTIPLE_INCORRECT_QUIZ, viewModel.inCorrectQuizList)
        //틀린 보기
        intent.putStringArrayListExtra(MULTIPLE_INCORRECT_BOGI, viewModel.inCorrectBogiList)

        startActivity(intent)
        finish()
    }

    override fun observeData() {
        viewModel.multipleChoiceQuizLiveData.observe(this){
            when(it){
                is MultipleChoiceQuizState.UnInitialized -> {
                    initViews()
                }
                is MultipleChoiceQuizState.Loading -> {
                    requestQuizList()
                }
                is MultipleChoiceQuizState.Success -> {
                    handleSuccessState(it)
                }
                is MultipleChoiceQuizState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleSuccessState(state: MultipleChoiceQuizState.Success) {
        binding.ActivityMultipleChoiceQuizGroup.isVisible = true
        binding.ActivityMultipleChoiceQuizProgressBar.isVisible = false

        //시작 시간 측정
        startTime = System.currentTimeMillis()

        state.quizList

        // 첫번째 문제 세팅하기
        setQuizByIndex(0)
    }

    private fun setQuizByIndex(index: Int) {

        // 문제 보여주기
        binding.ActivityMultipleChoiceQuizTextTextView.text = viewModel.getQuizText(index)

        //보기 보여주기
        val selectList = viewModel.getSelectText(index).split(".")
        originAnswer = selectList[0]

        val tempList = mutableListOf<String>().apply {
            add(selectList[0])
            add(selectList[1])
            add(selectList[2])
            add(selectList[3])
        }

        val shuffledList = tempList.shuffled()

        binding.ActivityMultipleChoiceQuizSelect1RadioButton.text = shuffledList[0]
        binding.ActivityMultipleChoiceQuizSelect2RadioButton.text = shuffledList[1]
        binding.ActivityMultipleChoiceQuizSelect3RadioButton.text = shuffledList[2]
        binding.ActivityMultipleChoiceQuizSelect4RadioButton.text = shuffledList[3]
    }

    private fun handleErrorState() {
        showToastMessage("오류가 발생했습니다")
        finish()
    }

    private fun requestQuizList() {
        //문제 받아오기
        val subjects = intent.getStringArrayListExtra(QUIZ_SUBJECT_LIST) ?: listOf<String>()
        viewModel.getMultipleQuizList(subjects.toList())
    }
}