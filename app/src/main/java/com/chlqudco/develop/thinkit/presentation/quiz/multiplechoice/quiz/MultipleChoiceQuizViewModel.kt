package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity
import com.chlqudco.develop.thinkit.domain.quiz.GetMultipleQuizUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MultipleChoiceQuizViewModel(
    private val getMultipleQuizUseCase: GetMultipleQuizUseCase
): BaseViewModel() {

    private val _multipleChoiceQuizLiveData = MutableLiveData<MultipleChoiceQuizState>(MultipleChoiceQuizState.UnInitialized)
    val multipleChoiceQuizLiveData: LiveData<MultipleChoiceQuizState> = _multipleChoiceQuizLiveData

    lateinit var quizList: List<MultipleChoiceEntity>
    var totalScore: Int = 0

    override fun fetchData(): Job = viewModelScope.launch {
        _multipleChoiceQuizLiveData.postValue(MultipleChoiceQuizState.Loading)
    }

    fun getMultipleQuizList(subjects: List<String>){
        viewModelScope.launch {
            val response = getMultipleQuizUseCase(subjects)
            if (response.isEmpty() || response[0].quizText.isEmpty()){
                _multipleChoiceQuizLiveData.postValue(MultipleChoiceQuizState.Error)
            } else{
                //문제 섞기
                quizList = response.shuffled()
                _multipleChoiceQuizLiveData.postValue(MultipleChoiceQuizState.Success(response))
            }
        }
    }

    fun getQuizText(index: Int): String{
        return quizList[index].quizText
    }

    fun getSelectText(index: Int): String{
        return quizList[index].quizSelect
    }
}