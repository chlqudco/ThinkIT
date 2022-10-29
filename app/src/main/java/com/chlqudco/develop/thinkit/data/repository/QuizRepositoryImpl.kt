package com.chlqudco.develop.thinkit.data.repository

import android.util.Log
import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity
import com.chlqudco.develop.thinkit.data.network.QuizApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class QuizRepositoryImpl(
    private val quizApiService: QuizApiService,
    private val ioDispatcher: CoroutineDispatcher
): QuizRepository {

    override suspend fun getMultipleChoiceQuizList(subjects: List<String>): List<MultipleChoiceEntity> = withContext(ioDispatcher) {
        try {
            val answer = mutableListOf<MultipleChoiceEntity>()
            val response = quizApiService.getMultipleChoice(subjects)
            if (response.isSuccessful){
                for (i in 0..9){
                    answer.add(MultipleChoiceEntity(response.body()?.problems?.get(i) ?: "", response.body()?.selects?.get(i) ?: ""))
                }
                return@withContext answer.toList()
            } else{
                return@withContext listOf()
            }
        } catch (exception: Exception) {
            return@withContext listOf()
        }
    }

    override suspend fun getSubjectiveQuizList(subjects: List<String>): List<String> = withContext(ioDispatcher){
        try {
            val response = quizApiService.getSubjectiveQuiz(subjects)
            if (response.isSuccessful){
                Log.e("asdasd", response.body().toString())
                return@withContext response.body()?.problems ?: listOf()
            } else{
                return@withContext listOf()
            }
        } catch (exception: Exception){
            return@withContext listOf()
        }
    }

}