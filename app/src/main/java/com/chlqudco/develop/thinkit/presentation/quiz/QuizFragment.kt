package com.chlqudco.develop.thinkit.presentation.quiz

import android.os.Bundle
import android.view.View
import com.chlqudco.develop.thinkit.databinding.FragmentQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class QuizFragment : BaseFragment<QuizViewModel, FragmentQuizBinding>() {

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
    }
}