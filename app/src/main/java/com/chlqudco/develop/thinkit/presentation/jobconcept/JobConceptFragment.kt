package com.chlqudco.develop.thinkit.presentation.jobconcept

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.FragmentJobConceptBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ANDROID
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATA_STRUCTURE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_SPRING
import org.koin.android.ext.android.inject

internal class JobConceptFragment : BaseFragment<JobConceptViewModel, FragmentJobConceptBinding>() {
    override val viewModel by inject<JobConceptViewModel>()

    override fun getViewBinding(): FragmentJobConceptBinding = FragmentJobConceptBinding.inflate(layoutInflater)

    override fun observeData() { }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //이름 바꾸기
        (activity as MainActivity).setTopTextViewText("Framework")

        //안드로이드 클릭
        binding.FragmentJobConceptAndroidLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_ANDROID)
            findNavController().navigate(R.id.action_jobConceptFragment_to_keywordsFragment)
        }

        //스프링 클릭
        binding.FragmentJobConceptSpringLayout.setOnClickListener {
            //프래그먼트 전환
            (activity as MainActivity).changeFragmentConceptToKeywords(KEYWORD_SPRING)
            findNavController().navigate(R.id.action_jobConceptFragment_to_keywordsFragment)
        }
    }

}