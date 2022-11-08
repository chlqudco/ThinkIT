package com.chlqudco.develop.thinkit.presentation.jobconcept

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class JobConceptViewModel(): BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {

    }

}