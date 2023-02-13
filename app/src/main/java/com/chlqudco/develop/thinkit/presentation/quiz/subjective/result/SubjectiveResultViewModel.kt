package com.chlqudco.develop.thinkit.presentation.quiz.subjective.result

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SubjectiveResultViewModel: BaseViewModel() {

    //사용자의 틀린 문제
    var inCorrectQuizList: ArrayList<String> = arrayListOf()
    //시용자의 틀린 문제 정답
    var inCorrectBogiList: ArrayList<String> = arrayListOf()
    override fun fetchData(): Job = viewModelScope.launch {  }
}