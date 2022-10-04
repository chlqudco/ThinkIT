package com.chlqudco.develop.thinkit.presentation.concept

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ConceptViewModel: BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {  }

}