package com.chlqudco.develop.thinkit.presentation.main

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {
    }
}