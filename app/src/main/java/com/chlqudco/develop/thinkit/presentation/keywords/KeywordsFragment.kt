package com.chlqudco.develop.thinkit.presentation.keywords

import android.os.Bundle
import android.view.View
import com.chlqudco.develop.thinkit.databinding.FragmentKeywordsBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class KeywordsFragment : BaseFragment<KeywordsViewModel, FragmentKeywordsBinding>() {
    override val viewModel by inject<KeywordsViewModel>()

    override fun getViewBinding(): FragmentKeywordsBinding = FragmentKeywordsBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //키워드 받아오기
    }

}