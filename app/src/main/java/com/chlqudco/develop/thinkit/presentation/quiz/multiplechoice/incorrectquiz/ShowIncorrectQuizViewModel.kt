package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ShowIncorrectQuizViewModel: BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {

    }

}