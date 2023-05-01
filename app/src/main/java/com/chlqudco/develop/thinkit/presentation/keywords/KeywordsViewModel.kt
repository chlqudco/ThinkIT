package com.chlqudco.develop.thinkit.presentation.keywords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.thinkit.data.entity.KeywordsEntity
import com.chlqudco.develop.thinkit.domain.concept.*
import com.chlqudco.develop.thinkit.domain.concept.DeleteKeywordDBUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsByQueryUseCase
import com.chlqudco.develop.thinkit.domain.concept.GetKeywordsUseCase
import com.chlqudco.develop.thinkit.domain.concept.InsertKeywordUseCase
import com.chlqudco.develop.thinkit.domain.mypage.GetUserRefreshTokenUseCase
import com.chlqudco.develop.thinkit.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class KeywordsViewModel(
    private val getKeywordsUseCase: GetKeywordsUseCase,
    private val getKeywordsByQueryUseCase: GetKeywordsByQueryUseCase,
    private val deleteKeywordDBUseCase: DeleteKeywordDBUseCase,
    private val insertKeywordUseCase: InsertKeywordUseCase,
    private val sendKeywordUseCase: SendFavoriteKeywordUseCase,
    private val getUserTokenUseCase: GetUserRefreshTokenUseCase
): BaseViewModel() {

    private var _keywordsStateLiveData = MutableLiveData<KeywordsState>(KeywordsState.UnInitialized)
    val keywordsStateLiveData: LiveData<KeywordsState> = _keywordsStateLiveData

    var _keywordListLiveData: MutableLiveData<List<String>> = MutableLiveData()

    val queryKeywords: MutableLiveData<List<String>> = MutableLiveData()

    override fun fetchData(): Job = viewModelScope.launch {
        _keywordsStateLiveData.postValue(KeywordsState.Loading)
    }

    //서버에서 키워드 받아오기
    fun getKeywords(subject: String){

        // 예외처리 1. 일어날리 없는 오류처리
        if (subject == "오류"){
            _keywordsStateLiveData.postValue(KeywordsState.Error)
            return
        }

        viewModelScope.launch {
            //일단 키워드들 변수에 저장해두고
            val response = getKeywordsUseCase(subject)

            //비어있으면 에러
            if (response.isEmpty()){
                _keywordsStateLiveData.postValue(KeywordsState.Error)
            }
            //뭔가 채워져 있으면
            else {
                //해당 개념 DB 다 지우기
                deleteKeywordDBUseCase(subject)


                //DB에 차곡차곡 모으기
                for(item in response){
                    val keyword = KeywordsEntity(0, subject, item)
                    insertKeyword(keyword)
                }

                //석세스로 들어가기
                _keywordsStateLiveData.postValue(KeywordsState.UnInitialized)
                _keywordsStateLiveData.postValue(KeywordsState.Success(response))


            }
        }
    }

    // 키워드로 검색값 가져오기
    fun getKeywordsByQuery(concept: String, query: String){
        viewModelScope.launch {
            //응답 가져오기
            val response = getKeywordsByQueryUseCase(concept)

            //쿼리에 포함되는 문자열만 골라내기
            val tempList: MutableList<String> = mutableListOf()
            for(item in response){
                //문자열에 키워드 들어있는지 비교
                if (item.contains(query, ignoreCase = true)){
                    tempList.add(item)
                }
            }

            //라이브데이터에 넣기
            queryKeywords.postValue(tempList)
        }
    }

    //DB 키워드 전체 받아오기
    fun getKeywordByDB(concept: String){
        //로직은 위랑 똑같은데 거르는 것만 없음
        viewModelScope.launch {
            val response = getKeywordsByQueryUseCase(concept)
            queryKeywords.postValue(response)
            //테스트를 위한 저장
            testKeywordsList.addAll(response)
        }
    }

    //즐겨찾기 전송 메소드
    fun sendFavoriteKeyword(keyword: String, token: String, isClicked: Boolean){
        viewModelScope.launch {
            sendKeywordUseCase(keyword, token, isClicked)
        }
    }

    suspend fun getUserToken(): String{
        return getUserTokenUseCase()
    }

    // DB에 키워드 저장
    fun insertKeyword(keyword: KeywordsEntity){
        viewModelScope.launch {
            insertKeywordUseCase(keyword)
        }
    }

    // for test
    val testKeywordsList = mutableListOf<String>()
}