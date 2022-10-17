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

    //전체 퀴즈 리스트
    lateinit var quizList: List<MultipleChoiceEntity>

    //사용자의 점수
    var totalScore: Int = 0

    //사용자의 틀린 문제
    var inCorrectQuizList: ArrayList<String> = arrayListOf()
    //시용자의 틀린 문제 정답
    var inCorrectBogiList: ArrayList<String> = arrayListOf()

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

    fun addInCorrectQuiz(quiz: String, bogi: String){
        inCorrectQuizList.add(quiz)
        inCorrectBogiList.add(bogi)
    }

}