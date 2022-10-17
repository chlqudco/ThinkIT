package com.chlqudco.develop.thinkit.presentation.concept

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.FragmentConceptBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class ConceptFragment : BaseFragment<ConceptViewModel, FragmentConceptBinding>() {

    override val viewModel by inject<ConceptViewModel>()

    override fun getViewBinding(): FragmentConceptBinding = FragmentConceptBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //이름 바꾸기
        (activity as MainActivity).setTopTextViewText("컴퓨터 공학")

        //자료구조
        binding.FragmentConceptDataStructureLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords("자료 구조")
        }

        //알고리즘
        binding.FragmentConceptAlgorithmLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords("알고리즘")
        }

        //운영체제
        binding.FragmentConceptOperatingSystemLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords("운영체제")
        }

        //데이터베이스
        binding.FragmentConceptDatabaseLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords("데이터베이스")
        }

        //네트워크
        binding.FragmentConceptNetworkLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords("네트워크")
        }

        //오류 제보
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
    }

    private fun sendFeedback(feedback: String) {
        //예외처리 : 아무것도 안적은 경우
        if (feedback.isEmpty()){
            return
        }

        //전송하기

    }
}