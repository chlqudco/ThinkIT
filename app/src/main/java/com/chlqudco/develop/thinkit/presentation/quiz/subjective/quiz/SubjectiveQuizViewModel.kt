package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.domain.quiz.GetSubjectiveQuizUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SubjectiveQuizViewModel(
    private val getSubjectiveQuizUseCase: GetSubjectiveQuizUseCase
) : BaseViewModel() {

    private val _subjectiveQuizLiveData = MutableLiveData<SubjectiveQuizState>(SubjectiveQuizState.UnInitialized)
    val subjectiveQuizLiveData: LiveData<SubjectiveQuizState> = _subjectiveQuizLiveData

    //전체 퀴즈 리스트
    lateinit var quizList: List<String>

    override fun fetchData(): Job = viewModelScope.launch {
        _subjectiveQuizLiveData.postValue(SubjectiveQuizState.Loading)
    }

    fun getSubjectiveQuizList(subjects: List<String>){
        viewModelScope.launch{
            val response = getSubjectiveQuizUseCase(subjects)
            if (response.isEmpty()){
                Log.e("qweqewqew","qwqeqeqeqeweqew")
                _subjectiveQuizLiveData.postValue(SubjectiveQuizState.Error)
            } else{
                quizList = response.shuffled()
                _subjectiveQuizLiveData.postValue(SubjectiveQuizState.Success(quizList))
            }
        }
    }

    fun getQuizText(index: Int): String{
        return quizList[index]
    }
}