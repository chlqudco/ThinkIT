package com.chlqudco.develop.thinkit.data.repository


interface ConceptRepository {

    suspend fun getKeywordsList(subject: String): List<String>

    suspend fun getContent(keyword: String): String

}