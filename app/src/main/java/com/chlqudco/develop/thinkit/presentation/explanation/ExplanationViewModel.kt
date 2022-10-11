package com.chlqudco.develop.thinkit.presentation.explanation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.concept.GetContentUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ExplanationViewModel(
private val getContentUseCase: GetContentUseCase
): BaseViewModel() {

    private var _explanationStateLiveData = MutableLiveData<ExplanationState>(ExplanationState.UnInitialized)
    val explanationStateLiveData: LiveData<ExplanationState> = _explanationStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _explanationStateLiveData.postValue(ExplanationState.Loading)
        val response = getContentUseCase("array")
        if (response.isEmpty()){
            _explanationStateLiveData.postValue(ExplanationState.Error)
        } else{
            _explanationStateLiveData.postValue(ExplanationState.Success(response))
        }
    }

    fun getExplanation(keyword: String){
        viewModelScope.launch {
            val response = getContentUseCase(keyword)
            if (response.isEmpty()){
                _explanationStateLiveData.postValue(ExplanationState.Error)
            } else{
                _explanationStateLiveData.postValue(ExplanationState.Success(response))
            }
        }
    }
}