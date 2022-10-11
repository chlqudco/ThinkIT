package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SubjectiveQuizViewModel : BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {  }
}