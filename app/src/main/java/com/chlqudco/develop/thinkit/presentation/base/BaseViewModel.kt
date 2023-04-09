package com.chlqudco.develop.thinkit.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.mypage.GetUserTokenUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal abstract class BaseViewModel(): ViewModel() {

    abstract fun fetchData(): Job

}