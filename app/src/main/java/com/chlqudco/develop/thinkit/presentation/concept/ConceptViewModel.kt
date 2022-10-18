package com.chlqudco.develop.thinkit.presentation.concept

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.feedback.PostFeedbackUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ConceptViewModel(
    private val postFeedbackUseCase: PostFeedbackUseCase
): BaseViewModel() {

    private val _feedbackLiveData = MutableLiveData<ConceptFeedbackState>(ConceptFeedbackState.UnInitialized)
    val feedbackLiveData: LiveData<ConceptFeedbackState> = _feedbackLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _feedbackLiveData.postValue(ConceptFeedbackState.Loading)
    }

    fun postFeedback(feedback: String){
        viewModelScope.launch{
            val response = postFeedbackUseCase(feedback)
            if (response){
                _feedbackLiveData.postValue(ConceptFeedbackState.Success(true))
            } else{
                _feedbackLiveData.postValue(ConceptFeedbackState.Error)
            }
        }
    }
}