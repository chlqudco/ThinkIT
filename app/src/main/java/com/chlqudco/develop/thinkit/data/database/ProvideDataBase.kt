package com.chlqudco.develop.thinkit.data.database

import android.content.Context
import androidx.room.Room

internal fun provideDataBase(context: Context): KeywordsDatabase =
    Room.databaseBuilder(context, KeywordsDatabase::class.java, KeywordsDatabase.DB_NAME).build()

internal fun provideDao(database: KeywordsDatabase) = database.keywordDao()