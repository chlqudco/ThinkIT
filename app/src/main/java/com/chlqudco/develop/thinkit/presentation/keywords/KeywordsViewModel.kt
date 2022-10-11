package com.chlqudco.develop.thinkit.presentation.keywords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class KeywordsViewModel(
    private val getKeywordsUseCase: GetKeywordsUseCase
): BaseViewModel() {

    private var _keywordsStateLiveData = MutableLiveData<KeywordsState>(KeywordsState.UnInitialized)
    val keywordsStateLiveData: LiveData<KeywordsState> = _keywordsStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _keywordsStateLiveData.postValue(KeywordsState.Loading)
    }

    fun getKeywords(subject: String){
        if (subject == "오류"){
            _keywordsStateLiveData.postValue(KeywordsState.Error)
            return
        }
        viewModelScope.launch {
            val response = getKeywordsUseCase(subject)
            if (response.isEmpty()){
                _keywordsStateLiveData.postValue(KeywordsState.Error)
            } else {
                _keywordsStateLiveData.postValue(KeywordsState.Success(response))
            }
        }
    }
}