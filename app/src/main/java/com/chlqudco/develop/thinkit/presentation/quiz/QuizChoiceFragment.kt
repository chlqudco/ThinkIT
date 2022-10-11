package com.chlqudco.develop.thinkit.presentation.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.chlqudco.develop.thinkit.databinding.FragmentQuizChoiceBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizActivity
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz.SubjectiveQuizActivity
import org.koin.android.ext.android.inject

internal class QuizChoiceFragment : BaseFragment<QuizChoiceViewModel, FragmentQuizChoiceBinding>() {

    private var sToast: Toast? = null

    override val viewModel by inject<QuizChoiceViewModel>()

    override fun getViewBinding(): FragmentQuizChoiceBinding = FragmentQuizChoiceBinding.inflate(layoutInflater)

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
        binding.FragmentQuizChoiceStartButton.setOnClickListener {

            //라디오버튼 아무것도 안누른 경우
            if (binding.FragmentQuizChoiceRadioGroup.checkedRadioButtonId == -1){
                context?.let { context -> showToastMessage(context, "운동을 선택해 주세요") }
                return@setOnClickListener
            }

            //객관식 누른 경우
            if (binding.FragmentQuizChoiceMultipleChoiceButton.isChecked){
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