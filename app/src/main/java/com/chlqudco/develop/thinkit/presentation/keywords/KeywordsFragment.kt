package com.chlqudco.develop.thinkit.presentation.keywords

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.FragmentKeywordsBinding
import com.chlqudco.develop.thinkit.presentation.adapter.KeywordAdapter
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

internal class KeywordsFragment : BaseFragment<KeywordsViewModel, FragmentKeywordsBinding>() {

    private lateinit var adapter: KeywordAdapter

    override val viewModel by inject<KeywordsViewModel>()

    override fun getViewBinding(): FragmentKeywordsBinding =
        FragmentKeywordsBinding.inflate(layoutInflater)

    private fun initViews() {

        //검색창 초기화
        binding.FragmentKeywordsSearchEditText.setText("")

        //로딩바 초기화
        binding.FragmentKeywordsProgressBar.isVisible = true
        binding.FragmentKeywordsEmptyTextView.isVisible = false

        CoroutineScope(Dispatchers.Main).launch {
            //어댑터 연결
            adapter = KeywordAdapter(viewModel.getUserToken(),
                keywordClickListener = {
                    (activity as MainActivity).changeFragmentKeywordsToExplanation(it)
                    findNavController().navigate(R.id.action_keywordsFragment_to_explanationWebViewFragment)
                },
                favoriteClickListener = { keyword, isClicked ->
                    sendKeyword(keyword, isClicked)
                })

            binding.FragmentKeywordsRecyclerView.adapter = adapter
            binding.FragmentKeywordsRecyclerView.layoutManager = LinearLayoutManager(context)

        }

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

        //즐겨찾기 여부 다 표시해야 되잖아, 가능 한 일?

    }

    private fun sendKeyword(keyword: String, isClicked: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            // 토큰 값 확인
            val userToken = viewModel.getUserToken()
            if (userToken.isEmpty()){
                showToastMessage("로그인 해주세요")
                return@launch
            }
            // 전송
            viewModel.sendFavoriteKeyword(keyword, userToken, isClicked)
        }
    }

    override fun onResume() {
        super.onResume()

        //제목 초기화
        (activity as MainActivity).setTopTextViewText((activity as MainActivity).userChoiceConcept)
        initViews()
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
        findNavController().navigate(R.id.action_keywordsFragment_to_conceptFragment)
    }

}