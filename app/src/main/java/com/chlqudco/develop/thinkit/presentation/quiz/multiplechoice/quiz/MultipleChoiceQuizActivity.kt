package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.chlqudco.develop.thinkit.databinding.ActivityMultipleChoiceQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result.MultipleChoiceResultActivity
import org.koin.android.ext.android.inject

internal class MultipleChoiceQuizActivity : BaseActivity<MultipleChoiceQuizViewModel, ActivityMultipleChoiceQuizBinding>() {

    private var startTime: Long = 0L
    private var endTime: Long = 0L

    private var quizCount : Int = 1

    override val viewModel: MultipleChoiceQuizViewModel by inject()

    override fun getViewBinding() = ActivityMultipleChoiceQuizBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //시작 시간 측정
        startTime = System.currentTimeMillis()

        initViews()
    }

    private fun initViews() {

        //다음 문제 버튼 클릭 리스너
        binding.ActivityMultipleChoiceQuizNextButton.setOnClickListener {
            // 마지막 문제인 경우
            if (quizCount == 10){

                //끝난 시간 측정
                endTime = System.currentTimeMillis()

                //결과 화면으로 이동
                val intent = Intent(this, MultipleChoiceResultActivity::class.java)
                intent.putExtra("time",endTime)

                startActivity(intent)
                finish()
            } else{
                quizCount++
                binding.ActivityMultipleChoiceQuizNumberTextView.text = "${quizCount}번째"
                if(quizCount==10){
                    binding.ActivityMultipleChoiceQuizNextButton.text = "퀴즈 종료"
                }
            }
        }

        //제출 버튼 클릭 리스너
        binding.ActivityMultipleChoiceQuizCheckCorrectButton.setOnClickListener {

            //아무것도 안 누른 경우
            if (binding.ActivityMultipleChoiceQuizRadioGroup.checkedRadioButtonId == -1){
                Toast.makeText(this, "보기를 골라주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }
}