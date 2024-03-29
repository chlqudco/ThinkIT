package com.chlqudco.develop.thinkit.presentation.quiz.subjective.choice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.chlqudco.develop.thinkit.databinding.FragmentSubjectiveQuizChoiceBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz.SubjectiveQuizActivity
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ANDROID
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ATTITUDE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_CBC
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_CS
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DEVELOPER
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_SPRING
import com.chlqudco.develop.thinkit.utility.AppKey.QUIZ_SUBJECT_LIST
import org.koin.android.ext.android.inject

internal class SubjectiveQuizChoiceFragment : BaseFragment<SubjectiveQuizChoiceViewModel, FragmentSubjectiveQuizChoiceBinding>() {

    override val viewModel by inject<SubjectiveQuizChoiceViewModel>()

    override fun getViewBinding(): FragmentSubjectiveQuizChoiceBinding = FragmentSubjectiveQuizChoiceBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //최병채 감추기
        binding.FragmentQuizChoiceCBCCheckBox.isVisible = viewModel.clickCount >= 5

        //이름 바꾸기
        (activity as MainActivity).setTopTextViewText("면접 대비")

        //시작버튼 클릭 리스너
        binding.FragmentSubjectiveQuizChoiceStartButton.setOnClickListener {

            //과목 뭐뭐 눌렀는지 확인하기
            val subjectList = arrayListOf<String>()

            if (binding.FragmentQuizChoiceAttitudeCheckBox.isChecked) { subjectList.add(KEYWORD_ATTITUDE) }
            if (binding.FragmentQuizChoiceCSCheckBox.isChecked) { subjectList.add(KEYWORD_CS) }
            if (binding.FragmentQuizChoiceDeveloperCheckBox.isChecked) { subjectList.add(KEYWORD_DEVELOPER) }
            if (binding.FragmentQuizChoiceAndroidCheckBox.isChecked) { subjectList.add(KEYWORD_ANDROID) }
            if (binding.FragmentQuizChoiceSpringCheckBox.isChecked) { subjectList.add(KEYWORD_SPRING) }

            //최병채
            if (binding.FragmentQuizChoiceCBCCheckBox.isChecked) { subjectList.add(KEYWORD_CBC) }

            //아무것도 체크 안한 경우 나가기
            if (subjectList.isEmpty()){
                showToastMessage("과목을 선택해 주세요")
                return@setOnClickListener
            }

            //면접 대비 시작
            val intent = Intent(activity, SubjectiveQuizActivity::class.java)
            intent.putStringArrayListExtra(QUIZ_SUBJECT_LIST, subjectList)
            startActivity(intent)

        }

        //최병채 나타나게 하기
        binding.FragmentSubjectiveQuizChoiceTopTextView.setOnClickListener {
            viewModel.clickCount++
            if(viewModel.clickCount > 5){
                binding.FragmentQuizChoiceCBCCheckBox.isVisible = true
            }
        }
    }


    override fun observeData() {}

}