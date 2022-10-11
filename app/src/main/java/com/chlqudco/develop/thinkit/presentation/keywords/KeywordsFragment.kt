package com.chlqudco.develop.thinkit.presentation.keywords

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.thinkit.databinding.FragmentKeywordsBinding
import com.chlqudco.develop.thinkit.presentation.adapter.KeywordAdapter
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import org.koin.android.ext.android.inject

internal class KeywordsFragment : BaseFragment<KeywordsViewModel, FragmentKeywordsBinding>() {

    private val adapter = KeywordAdapter()

    override val viewModel by inject<KeywordsViewModel>()

    override fun getViewBinding(): FragmentKeywordsBinding = FragmentKeywordsBinding.inflate(layoutInflater)

    private fun initViews() {
        //어댑터 연결
        binding.FragmentKeywordsRecyclerView.adapter = adapter
        binding.FragmentKeywordsRecyclerView.layoutManager = LinearLayoutManager(context)


    }

    override fun observeData() {
        viewModel.keywordsStateLiveData.observe(this){
            when(it){
                is KeywordsState.UnInitialized -> {
                    initViews()
                }
                is KeywordsState.Loading -> {

                }
                is KeywordsState.Success -> {
                    handleSuccessState(it)
                }
                is KeywordsState.Error -> {

                }
            }
        }
    }

    private fun handleSuccessState(state: KeywordsState.Success){
        //비어있는 경우
        if (state.keywordsList.isEmpty()){
            Toast.makeText(context,"불러오지 못했습니다", Toast.LENGTH_SHORT).show()
            binding.FragmentKeywordsEmptyTextView.isVisible = true
            binding.FragmentKeywordsRecyclerView.isVisible = false
        } else{
            adapter.keywordList = state.keywordsList
            adapter.notifyDataSetChanged()
            binding.FragmentKeywordsEmptyTextView.isVisible = false
            binding.FragmentKeywordsRecyclerView.isVisible = true
        }
    }

}