package com.chlqudco.develop.thinkit.presentation.signup

import com.chlqudco.develop.thinkit.databinding.ActivitySignUpBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

internal class SignUpActivity : BaseActivity<SignUpViewModel, ActivitySignUpBinding>() {

    override val viewModel: SignUpViewModel by inject()

    override fun getViewBinding() = ActivitySignUpBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.signUpStateLiveData.observe(this){
            when(it){
                is SignUpState.UnInitialized -> {
                    initViews()
                }
                is SignUpState.Loading -> {

                }
                is SignUpState.Success -> {
                    handleSuccessState()
                }
                is SignUpState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun initViews() {
        binding.SignUpSignUpButton.setOnClickListener {
            signUp()
        }

    }

    private fun signUp() = with(binding){

        //예외처리 1. 안 채운 곳이 있을 경우
        if (SignUpIdEditText.text.isNullOrEmpty() || SignUpPasswordEditText.text.isNullOrEmpty() || SignUpPasswordCheckEditText.text.isNullOrEmpty() || SignUpNameEditText.text.isNullOrEmpty()) {
            showToastMessage("모두 입력해 주세요")
            return
        }

        //예외처리 2. 비밀번호와 비밀번호 확인이 다른 경우
        if (SignUpPasswordEditText.text.toString() != SignUpPasswordCheckEditText.text.toString()) {
            showToastMessage("비밀번호가 다릅니다")
            return
        }

        //회원 가입 시작
        val userId = SignUpIdEditText.text.toString()
        val password = SignUpPasswordEditText.text.toString()
        val userNickName = SignUpNameEditText.text.toString()

        viewModel.signUp(userId,password,userNickName)
    }

    private fun handleSuccessState() {
        showToastMessage("회원가입에 성공했습니다")
        finish()
    }

    private fun handleErrorState() {
        showToastMessage("실패!")
    }

}