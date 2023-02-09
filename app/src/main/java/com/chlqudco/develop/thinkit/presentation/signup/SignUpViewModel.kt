package com.chlqudco.develop.thinkit.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.mypage.SignUpUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
): BaseViewModel() {

    private val _signUpStateLiveData = MutableLiveData<SignUpState>(SignUpState.UnInitialized)
    val signUpStateLiveData: LiveData<SignUpState> = _signUpStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _signUpStateLiveData.postValue(SignUpState.UnInitialized)
        _signUpStateLiveData.postValue(SignUpState.Loading)
    }

    fun signUp(userName: String, password: String, nickName: String){
        viewModelScope.launch {
            val response = signUpUseCase(userName, password, nickName)
            if (response == "fail" || response =="" || response == "exist"){
                _signUpStateLiveData.postValue(SignUpState.Error)
            } else{
                _signUpStateLiveData.postValue(SignUpState.Success(response))
            }
        }
    }
}