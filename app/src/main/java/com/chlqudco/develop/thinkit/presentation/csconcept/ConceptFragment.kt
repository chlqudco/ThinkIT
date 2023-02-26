package com.chlqudco.develop.thinkit.presentation.csconcept

import androidx.navigation.fragment.findNavController
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.FragmentConceptBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_AI
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ALGORITHM
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATABASE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATA_STRUCTURE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_NETWORK
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_OS
import org.koin.android.ext.android.inject

internal class ConceptFragment : BaseFragment<ConceptViewModel, FragmentConceptBinding>() {

    override val viewModel by inject<ConceptViewModel>()

    override fun getViewBinding(): FragmentConceptBinding = FragmentConceptBinding.inflate(layoutInflater)

    private fun initViews() {
        //이름 바꾸기
        (activity as MainActivity).setTopTextViewText("컴퓨터 공학")

        //자료구조
        binding.FragmentConceptDataStructureLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_DATA_STRUCTURE)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }

        //알고리즘
        binding.FragmentConceptAlgorithmLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_ALGORITHM)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }

        //운영체제
        binding.FragmentConceptOperatingSystemLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_OS)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }

        //데이터베이스
        binding.FragmentConceptDatabaseLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_DATABASE)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }

        //네트워크
        binding.FragmentConceptNetworkLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_NETWORK)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }

        //인공지능
        binding.FragmentConceptAILayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_AI)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }

        //컴퓨터 구조
        /*
        binding.FragmentConceptComputerStructureLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_COMPUTER_STRUCTURE)
            findNavController().navigate(R.id.action_conceptFragment_to_keywordsFragment)
        }
        */

        //오류 제보
        /*
        binding.FragmentConceptFeedbackButton.setOnClickListener {
            val editText = EditText(context)

            AlertDialog.Builder(context)
                .setTitle("오류를 적어주세요")
                .setView(editText)
                .setPositiveButton("전송") { _, _ ->
                    sendFeedback(editText.text.toString())
                }
                .setNegativeButton("나가기") { _, _ -> }
                .create()
                .show()
        }

         */
    }

    override fun observeData() {
        viewModel.feedbackLiveData.observe(this){
            when(it){
                is ConceptFeedbackState.UnInitialized -> { initViews() }
                is ConceptFeedbackState.Loading -> { }
                is ConceptFeedbackState.Success -> { handleFeedbackSuccess() }
                is ConceptFeedbackState.Error -> { handleFeedbackError() }
            }
        }
    }

    private fun handleFeedbackError() {
        showToastMessage("전송에 실패했습니다.")
    }

    private fun handleFeedbackSuccess() {
        showToastMessage("전송이 완료되었습니다. 감사합니다")
    }

    private fun sendFeedback(feedback: String) {
        //예외처리 : 아무것도 안적은 경우
        if (feedback.isEmpty()){
            return
        }

        //전송하기
        viewModel.postFeedback(feedback)
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }
}