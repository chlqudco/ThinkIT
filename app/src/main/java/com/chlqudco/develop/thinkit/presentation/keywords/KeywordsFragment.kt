package com.chlqudco.develop.thinkit.presentation.keywords

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.thinkit.databinding.FragmentKeywordsBinding
import com.chlqudco.develop.thinkit.presentation.adapter.KeywordAdapter
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class KeywordsFragment : BaseFragment<KeywordsViewModel, FragmentKeywordsBinding>() {

    private lateinit var adapter : KeywordAdapter

    override val viewModel by inject<KeywordsViewModel>()

    override fun getViewBinding(): FragmentKeywordsBinding = FragmentKeywordsBinding.inflate(layoutInflater)

    private fun initViews() {
        binding.FragmentKeywordsProgressBar.isVisible = true
        binding.FragmentKeywordsEmptyTextView.isVisible = false

        //어댑터 연결
        adapter = KeywordAdapter(keywordClickListener = {
            (activity as MainActivity).changeFragmentKeywordsToExplanation(it)
        })
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
                    requestKeywords()
                }
                is KeywordsState.Success -> {
                    handleSuccessState(it)
                }
                is KeywordsState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun requestKeywords() {
        viewModel.getKeywords((activity as MainActivity).getSubject())
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
        binding.FragmentKeywordsProgressBar.isVisible = false
        binding.FragmentKeywordsEmptyTextView.isVisible = false
    }

    private fun handleErrorState(){
        Toast.makeText(context, "오류가 발생했습니다", Toast.LENGTH_SHORT).show()
        binding.FragmentKeywordsProgressBar.isVisible = false
        binding.FragmentKeywordsEmptyTextView.isVisible = true
    }

}