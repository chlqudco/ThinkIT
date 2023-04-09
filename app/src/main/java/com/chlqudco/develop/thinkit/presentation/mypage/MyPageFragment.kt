package com.chlqudco.develop.thinkit.presentation.mypage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.chlqudco.develop.thinkit.databinding.FragmentMyPageBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz.MultipleChoiceQuizActivity
import com.chlqudco.develop.thinkit.presentation.signup.SignUpActivity
import com.chlqudco.develop.thinkit.utility.AppKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

internal class MyPageFragment : BaseFragment<MyPageViewModel, FragmentMyPageBinding>() {

    override val viewModel by inject<MyPageViewModel>()

    override fun getViewBinding(): FragmentMyPageBinding =
        FragmentMyPageBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.logInStateLiveData.observe(this) {
            when (it) {
                is MyPageState.UnInitialized -> {
                    initViews()
                }
                is MyPageState.Loading -> {}
                is MyPageState.Success -> {
                    handleSuccessState(it)
                }
                is MyPageState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        //-1. 이름 바꾸기
        (activity as MainActivity).setTopTextViewText("내 정보")

        //0. 리스너 등록
        registerListener()

        //1. 로그인 되어 있는지 확인하기
        CoroutineScope(Dispatchers.Main).launch {
            val isLogIn = viewModel.checkLogIn()

            //2. 로그인 되어 있다면 내 정보 화면 보여주기
            if (isLogIn) {
                //마이페이지 화면 불러오기
                showMyPageView()
                //이름 초기화
                binding.myPageNameTextView.text = "${viewModel.getNickName()}님 반갑습니다"
            }

            //3. 로그인 안되어 있으면 가만히 있어
            else {
                hideMyPageView()
            }
        }

    }

    private fun showMyPageView() {
        binding.myPageMyPageConstLayout.isVisible = true
        binding.myPageLogInUdoConstLayout.isVisible = false
    }

    private fun hideMyPageView() {
        binding.myPageMyPageConstLayout.isVisible = false
        binding.myPageLogInUdoConstLayout.isVisible = true
    }

    private fun registerListener() = with(binding) {
        //로그인 버튼
        myPageLogInButton.setOnClickListener {
            logIn()
        }

        //로그아웃 버튼
        myPageLogOutButton.setOnClickListener {
            logOut()
        }

        //회원가입 버튼
        myPageSignUpButton.setOnClickListener {
            moveSignUpActivity()
        }

        //개념 즐겨찾기
        myPageFavoriteConceptButton.setOnClickListener {
            showToastMessage("미구현입니다")
        }

        //저장된 틀린 문제
        myPageSaveQuizButton.setOnClickListener {
            showToastMessage("미구현입니다")
        }

        //오류 제보
        myPageSendErrorButton.setOnClickListener {
            showToastMessage("미구현입니다")
        }

        //나만의 문제
        myPageMyQuizButton.setOnClickListener {
            showToastMessage("미구현입니다")
        }
    }

    private fun logIn() {
        val userName = binding.myPageEmailEditText.text.toString()
        val password = binding.myPagePasswordEditText.text.toString()

        viewModel.logIn(userName, password)
    }

    private fun logOut() {
        CoroutineScope(Dispatchers.Main).launch{
            viewModel.logOut()
            hideMyPageView()
        }
    }

    private fun moveSignUpActivity() {
        val intent = Intent(activity, SignUpActivity::class.java)
        startActivity(intent)
    }


    @SuppressLint("SetTextI18n")
    private fun handleSuccessState(state: MyPageState.Success) {
        showToastMessage("로그인 성공")
        showMyPageView()
        binding.myPageNameTextView.text = "${state.nickName}님 반갑습니다"
    }

    private fun handleErrorState() {
        showToastMessage("로그인 실패")
    }

}