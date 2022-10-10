package com.chlqudco.develop.thinkit.presentation.explanation

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.chlqudco.develop.thinkit.databinding.FragmentExplanationBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class ExplanationFragment : BaseFragment<ExplanationViewModel, FragmentExplanationBinding>() {

    override val viewModel by inject<ExplanationViewModel>()

    override fun getViewBinding(): FragmentExplanationBinding = FragmentExplanationBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //이름 바꾸기
        (activity as MainActivity).setTopTextViewText("???")

        //스크롤설정
        binding.ExplanationMainTextView.movementMethod = ScrollingMovementMethod()

        //키워드 받아오기
    }

}