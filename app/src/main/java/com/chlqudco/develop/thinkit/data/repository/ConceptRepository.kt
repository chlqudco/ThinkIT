package com.chlqudco.develop.thinkit.data.repository

interface ConceptRepository {

    suspend fun getKeywordsList(subject: String): List<String>
}