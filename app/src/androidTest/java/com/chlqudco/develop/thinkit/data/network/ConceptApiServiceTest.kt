package com.chlqudco.develop.thinkit.data.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_AI
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_ALGORITHM
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATABASE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_DATA_STRUCTURE
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_NETWORK
import com.chlqudco.develop.thinkit.utility.AppKey.KEYWORD_OS
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ConceptApiServiceTest: KoinTest{

    private val conceptApiService: ConceptApiService by inject()

    // 과목에 대한 키워드들을 잘 받아오는지 테스트

    @Test
    fun get_data_structure_keyword_test() = runTest {

        //값 받아오기
        val response = conceptApiService.getKeywords(KEYWORD_DATA_STRUCTURE)

        //성공여부 확인
        assertThat(response.isSuccessful).isTrue()

        //내부 자료 확인
        assertThat(response.body()?.keywordsList).contains("스택")
    }

    @Test
    fun get_algorithm_keyword_test() = runTest {

        //값 받아오기
        val response = conceptApiService.getKeywords(KEYWORD_ALGORITHM)

        //성공여부 확인
        assertThat(response.isSuccessful).isTrue()

        //내부 자료 확인
        assertThat(response.body()?.keywordsList).contains("재귀")
    }

    @Test
    fun get_os_keyword_test() = runTest {

        //값 받아오기
        val response = conceptApiService.getKeywords(KEYWORD_OS)

        //성공여부 확인
        assertThat(response.isSuccessful).isTrue()

        //내부 자료 확인
        assertThat(response.body()?.keywordsList).contains("쓰레드")
    }

    @Test
    fun get_database_keyword_test() = runTest {

        //값 받아오기
        val response = conceptApiService.getKeywords(KEYWORD_DATABASE)

        //성공여부 확인
        assertThat(response.isSuccessful).isTrue()

        //내부 자료 확인
        assertThat(response.body()?.keywordsList).contains("DBMS")
    }

    @Test
    fun get_network_keyword_test() = runTest {

        //값 받아오기
        val response = conceptApiService.getKeywords(KEYWORD_NETWORK)

        //성공여부 확인
        assertThat(response.isSuccessful).isTrue()

        //내부 자료 확인
        assertThat(response.body()?.keywordsList).contains("HTTP")
    }

    @Test
    fun get_ai_keyword_test() = runTest {

        //값 받아오기
        val response = conceptApiService.getKeywords(KEYWORD_AI)

        //성공여부 확인
        assertThat(response.isSuccessful).isTrue()

        //내부 자료 확인
        assertThat(response.body()?.keywordsList).contains("인공지능")
    }


}
// @Body 를 사용하는 테스트는 해쉬맵을 만들어서 전달하라
/*
    val hashMap = hashMapOf<String, Any>(
    CD_IDX to 1,
    CONTENT to "테스트 문자열22"
)

val call: Response<String> = service.createUserPost(
    "Bearer 대충 토큰 형태의 문자열",
    hashMap
)
 */
