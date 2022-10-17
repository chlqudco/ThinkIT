package com.chlqudco.develop.thinkit.data.repository

import com.chlqudco.develop.thinkit.data.entity.MultipleChoiceEntity

interface QuizRepository {
    suspend fun getMultipleChoiceQuizList(subjects: List<String>): List<MultipleChoiceEntity>

    suspend fun getSubjectiveQuizList(subjects: List<String>): List<String>
}