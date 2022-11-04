package com.chlqudco.develop.thinkit.presentation.keywords

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.thinkit.databinding.FragmentKeywordsBinding
import com.chlqudco.develop.thinkit.presentation.adapter.KeywordAdapter
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.utility.AppKey.SEARCH_BOOKS_TIME_DELAY
import org.koin.android.ext.android.inject

internal class KeywordsFragment : BaseFragment<KeywordsViewModel, FragmentKeywordsBinding>() {

    private lateinit var adapter: KeywordAdapter

    override val viewModel by inject<KeywordsViewModel>()

    override fun getViewBinding(): FragmentKeywordsBinding =
        FragmentKeywordsBinding.inflate(layoutInflater)

    private fun initViews() {

        //DB 초기화


        //로딩바 초기화
        binding.FragmentKeywordsProgressBar.isVisible = true
        binding.FragmentKeywordsEmptyTextView.isVisible = false

        //어댑터 연결
        adapter = KeywordAdapter(keywordClickListener = {
            (activity as MainActivity).changeFragmentKeywordsToExplanation(it)
        })
        binding.FragmentKeywordsRecyclerView.adapter = adapter
        binding.FragmentKeywordsRecyclerView.layoutManager = LinearLayoutManager(context)


        //검색 창 연결
        binding.FragmentKeywordsSearchEditText.addTextChangedListener { text ->

            //입력된게 없는 경우 : 다 지운 경우, 키워드 전체 받아오기
            if (text.toString().isEmpty()) {
                viewModel.getKeywordByDB((activity as MainActivity).getSubject())
            }
            //입력된게 있으면 DB 검색
            else {
                viewModel.getKeywordsByQuery(
                    (activity as MainActivity).getSubject(),
                    binding.FragmentKeywordsSearchEditText.text.toString()
                )
            }
        }

        //검색 리스트 옵저빙
        viewModel.queryKeywords.observe(viewLifecycleOwner) {
            adapter.keywordList = it
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()

        //제목 초기화
        (activity as MainActivity).setTopTextViewText((activity as MainActivity).userChoiceConcept)
    }

    override fun observeData() {
        viewModel.keywordsStateLiveData.observe(this) {
            when (it) {
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

    @SuppressLint("NotifyDataSetChanged")
    private fun handleSuccessState(state: KeywordsState.Success) {
        //비어있는 경우
        if (state.keywordsList.isEmpty()) {
            showToastMessage("불러오지 못했습니다")
            binding.FragmentKeywordsEmptyTextView.isVisible = true
            binding.FragmentKeywordsRecyclerView.isVisible = false
        } else {
            adapter.keywordList = state.keywordsList
            adapter.notifyDataSetChanged()
            binding.FragmentKeywordsEmptyTextView.isVisible = false
            binding.FragmentKeywordsRecyclerView.isVisible = true
        }
        binding.FragmentKeywordsProgressBar.isVisible = false
        binding.FragmentKeywordsEmptyTextView.isVisible = false
    }

    private fun handleErrorState() {
        showToastMessage("오류가 발생했습니다")
        binding.FragmentKeywordsProgressBar.isVisible = false
        binding.FragmentKeywordsEmptyTextView.isVisible = true
    }

}