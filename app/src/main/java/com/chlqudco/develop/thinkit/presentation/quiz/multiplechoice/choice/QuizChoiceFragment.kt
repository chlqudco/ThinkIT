package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.choice

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chlqudco.develop.thinkit.databinding.FragmentQuizChoiceBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizActivity
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz.SubjectiveQuizActivity
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_AI
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ALGORITHM
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ANDROID
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_COMPUTER_STRUCTURE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATABASE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATA_STRUCTURE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_NETWORK
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_OS
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_SPRING
import com.chlqudco.develop.thinkit.utility.AppKey.QUIZ_SUBJECT_LIST
import org.koin.android.ext.android.inject

internal class QuizChoiceFragment : BaseFragment<QuizChoiceViewModel, FragmentQuizChoiceBinding>() {

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
        (activity as MainActivity).setTopTextViewText("객관식 퀴즈")

        //시작 버튼
        binding.FragmentQuizChoiceStartButton.setOnClickListener {

            //과목 뭐뭐 눌렀는지 확인하기
            val subjectList = arrayListOf<String>()

            if (binding.FragmentQuizChoiceDataStructureCheckBox.isChecked) { subjectList.add(KEYWORD_DATA_STRUCTURE) }
            if (binding.FragmentQuizChoiceAlgorithmCheckBox.isChecked) { subjectList.add(KEYWORD_ALGORITHM) }
            if (binding.FragmentQuizChoiceOSCheckBox.isChecked) { subjectList.add(KEYWORD_OS) }
            if (binding.FragmentQuizChoiceDataBaseCheckBox.isChecked) { subjectList.add(KEYWORD_DATABASE) }
            if (binding.FragmentQuizChoiceNetworkCheckBox.isChecked) { subjectList.add(KEYWORD_NETWORK) }
            if (binding.FragmentQuizChoiceAICheckBox.isChecked) { subjectList.add(KEYWORD_AI) }
            //if (binding.FragmentQuizChoiceComputerStructureCheckBox.isChecked) { subjectList.add(KEYWORD_COMPUTER_STRUCTURE) }
            if (binding.FragmentQuizChoiceAndroidCheckBox.isChecked) { subjectList.add(KEYWORD_ANDROID) }
            if (binding.FragmentQuizChoiceSpringCheckBox.isChecked) { subjectList.add(KEYWORD_SPRING) }

            //아무것도 체크 안한 경우 나가기
            if (subjectList.isEmpty()){
                showToastMessage("과목을 선택해 주세요")
                return@setOnClickListener
            }

            //객관식 퀴즈 시작
            val intent = Intent(activity, MultipleChoiceQuizActivity::class.java)

            intent.putStringArrayListExtra(QUIZ_SUBJECT_LIST, subjectList)
            startActivity(intent)


        }
    }

}