package com.chlqudco.develop.thinkit.presentation.concept

import android.os.Bundle
import android.view.View
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

        //리스너 달기
        binding.ConceptDataStructureLayout.setOnClickListener {
            //여기서 이름 바꾸기
            (activity as MainActivity).setTopTextViewText("자료 구조")
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords()
        }
    }
}