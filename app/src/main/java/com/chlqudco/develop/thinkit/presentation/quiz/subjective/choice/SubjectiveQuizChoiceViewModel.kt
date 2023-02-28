package com.chlqudco.develop.thinkit.presentation.quiz.subjective.choice

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SubjectiveQuizChoiceViewModel: BaseViewModel() {

    //최병채 히든 버튼
    var clickCount = 0

    override fun fetchData(): Job = viewModelScope.launch {  }

}