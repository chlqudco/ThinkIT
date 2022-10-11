package com.chlqudco.develop.thinkit.presentation.explanation

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.chlqudco.develop.thinkit.databinding.FragmentExplanationBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class ExplanationFragment : BaseFragment<ExplanationViewModel, FragmentExplanationBinding>() {

    override val viewModel by inject<ExplanationViewModel>()

    override fun getViewBinding(): FragmentExplanationBinding = FragmentExplanationBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //스크롤설정
        binding.FragmentExplanationMainTextView.movementMethod = ScrollingMovementMethod()

        //글씨 초기화
        binding.FragmentExplanationMainTextView.text = ""
    }

    override fun observeData() {
        viewModel.explanationStateLiveData.observe(this){
            when(it){
                is ExplanationState.Error -> handleErrorState()
                is ExplanationState.Loading -> {
                    requestExplanation()
                }
                is ExplanationState.Success -> handleSuccessState(it)
                is ExplanationState.UnInitialized -> {}
            }
        }
    }

    private fun requestExplanation() {
        binding.FragmentExplanationProgressBar.isVisible = true
        viewModel.getExplanation((activity as MainActivity).userChoiceKeyword)
    }

    private fun handleSuccessState(state: ExplanationState.Success){
        binding.FragmentExplanationMainTextView.text = Html.fromHtml(state.content, Html.FROM_HTML_MODE_LEGACY)
        binding.FragmentExplanationProgressBar.isVisible = false
    }

    private fun handleErrorState(){
        Toast.makeText(context, "오류가 발생했습니다", Toast.LENGTH_SHORT).show()
        binding.FragmentExplanationProgressBar.isVisible = false
    }


}