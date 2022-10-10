package com.chlqudco.develop.thinkit.presentation.explanation

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ExplanationViewModel: BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {  }
}