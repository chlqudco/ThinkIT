package com.chlqudco.develop.thinkit.utility

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object AppKey {

    const val KEYWORD_DATA_STRUCTURE = "자료구조"
    const val KEYWORD_ALGORITHM = "알고리즘"
    const val KEYWORD_DATABASE = "데이터베이스"
    const val KEYWORD_OS = "운영체제"
    const val KEYWORD_NETWORK = "네트워크"
    const val KEYWORD_AI = "인공지능"

    const val KEYWORD_ANDROID = "안드로이드"
    const val KEYWORD_SPRING = "스프링"

    const val KEYWORD_CBC = "private"

    const val KEYWORD_ATTITUDE = "인성"
    const val KEYWORD_CS = "CS"
    const val KEYWORD_DEVELOPER = "개발자 공통"

    const val QUIZ_SUBJECT_LIST = "QuizSubjectList"

    const val MULTIPLE_QUIZ_TIME = "TimeTime"
    const val MULTIPLE_QUIZ_SCORE = "ScoreTime"

    const val SUBJECTIVE_QUIZ_TIME = "subTime"

    const val MULTIPLE_INCORRECT_QUIZ = "InCorrectQuiz"
    const val MULTIPLE_INCORRECT_BOGI = "InCorrectBogi"

    const val DATASTORE_NAME = "myPageToken"

    val DATASTORE_USER_ACCESS_TOKEN = stringPreferencesKey("user_access_token")
    val DATASTORE_USER_REFRESH_TOKEN = stringPreferencesKey("user_refresh_token")
    val DATASTORE_USER_NICKNAME = stringPreferencesKey("user_nickname")
    val DATASTORE_USER_CHAT_ROOM_ID = longPreferencesKey("user_chat_room_id")

}