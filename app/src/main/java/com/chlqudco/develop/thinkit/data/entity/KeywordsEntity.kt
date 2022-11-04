package com.chlqudco.develop.thinkit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeywordsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val concept: String,
    val keyword: String
)