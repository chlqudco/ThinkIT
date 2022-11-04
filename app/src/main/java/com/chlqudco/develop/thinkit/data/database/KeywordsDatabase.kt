package com.chlqudco.develop.thinkit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chlqudco.develop.thinkit.data.database.dao.KeywordDao
import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity

@Database(
    entities = [KeywordsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KeywordsDatabase: RoomDatabase() {

    companion object{
        const val DB_NAME = "KeywordsDatabase.db"
    }

    abstract fun keywordDao(): KeywordDao
}