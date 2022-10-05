package com.chlqudco.develop.thinkit.presentation.keywords

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class KeywordsViewModel: BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {  }
}