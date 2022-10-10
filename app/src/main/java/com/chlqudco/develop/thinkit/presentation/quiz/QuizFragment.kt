package com.chlqudco.develop.thinkit.presentation.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.chlqudco.develop.thinkit.databinding.FragmentQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.presentation.multiplechoice.quiz.MultipleChoiceQuizActivity
import com.chlqudco.develop.thinkit.presentation.subjective.quiz.SubjectiveQuizActivity
import org.koin.android.ext.android.inject

internal class QuizFragment : BaseFragment<QuizViewModel, FragmentQuizBinding>() {

    private var sToast: Toast? = null

    override val viewModel by inject<QuizViewModel>()

    override fun getViewBinding(): FragmentQuizBinding = FragmentQuizBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //이름 바꾸기
        (activity as MainActivity).setTopTextViewText("퀴즈 선택")

        //시작 버튼
        binding.QuizStartButton.setOnClickListener {

            //라디오버튼 아무것도 안누른 경우
            if (binding.quizRadioGroup.checkedRadioButtonId == -1){
                context?.let { context -> showToastMessage(context, "운동을 선택해 주세요") }
                return@setOnClickListener
            }

            //객관식 누른 경우
            if (binding.quizMultipleChoiceButton.isChecked){
                val intent = Intent(activity, MultipleChoiceQuizActivity::class.java)
                startActivity(intent)
            }
            //면접대비 누른 경우
            else{
                val intent = Intent(activity, SubjectiveQuizActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //토스트 메세지 띄우기
    private fun showToastMessage(context: Context, message: String) {
        if (sToast == null) {
            sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        } else {
            sToast!!.setText(message)
        }
        sToast?.show()
    }
}