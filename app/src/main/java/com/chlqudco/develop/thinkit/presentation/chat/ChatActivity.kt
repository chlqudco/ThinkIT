package com.chlqudco.develop.thinkit.presentation.chat

import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.thinkit.data.model.Chat
import com.chlqudco.develop.thinkit.data.network.Url
import com.chlqudco.develop.thinkit.databinding.ActivityChatBinding
import com.chlqudco.develop.thinkit.presentation.adapter.ChatListAdapter
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import io.socket.client.IO
import org.koin.android.ext.android.inject
import io.socket.client.Socket

class ChatActivity : BaseActivity<ChatViewModel, ActivityChatBinding>() {

    private lateinit var adapter: ChatListAdapter

    override val viewModel by inject<ChatViewModel>()

    override fun getViewBinding() = ActivityChatBinding.inflate(layoutInflater)

    override fun observeData() {
        // 데이터스토어에 있는 RoomId 가져오기
        viewModel.chatRoomIdDataStoreLiveData.observe(this){
            when{
                it < 0 ->{
                    // -1을 반환하면 새 채팅방 생성
                    viewModel.getChatRoomId()
                }
            }
        }

        viewModel.chatRoomIdStateLiveData.observe(this){
            when(it){
                is ChatState.UnInitialized -> {
                    initViews()
                }
                is ChatState.Loading -> {

                }
                is ChatState.Success -> {
                    handleSuccessState(it)
                }
                is ChatState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleErrorState() {

    }

    private fun handleSuccessState(state: ChatState.Success) {

    }

    private fun initViews() {
        // 어댑터 연결
        adapter = ChatListAdapter()
        binding.ActivityChatRecyclerView.adapter = adapter
        binding.ActivityChatRecyclerView.layoutManager = LinearLayoutManager(this)

        // 채팅방 만든 적 없으면 채팅룸 만들기
        viewModel.getChatRoomIdToDataStore()

        // 버튼 바인딩
        binding.ActivityChatSendButton.setOnClickListener {
            sendMessage()
        }

        // 테스트
        val socket: Socket = IO.socket(Url.THINK_IT_BASE_URL)
        socket.connect()



        val chatList = mutableListOf<Chat>()
        chatList.apply {
            add(
                Chat(
                    id = 0,
                    myChat = false,
                    message = "안녕하세요, 오류를 제보하거나 궁금한 사항이 있다면 물어봐주세요.",
                    createdAt = System.currentTimeMillis() - 1000000
                )
            )
            add(
                Chat(
                    id = 5,
                    myChat = true,
                    message = "자료구조의 스택 키워드에 대한 설명중 ~~ 부분이 잘못되었습니다",
                    createdAt = System.currentTimeMillis() - 1000000
                )
            )
            add(
                Chat(
                    id = 1,
                    myChat = false,
                    message = "감사합니다. 수정했습니다.",
                    createdAt = System.currentTimeMillis() - 100000
                )
            )
            add(
                Chat(
                    id = 2,
                    myChat = true,
                    message = "아닙니다. 앞으로도 많은 자료 부탁드립니다.",
                    createdAt = System.currentTimeMillis()
                )
            )
        }
        adapter.submitList(chatList)
    }

    // 전송 버튼을 눌렀을 때 서버에 전송하기
    private fun sendMessage() {
        // 작성한 메세지
        val message = binding.ActivityChatMessageEditText.text.toString()

        // 예외처리 1. 빈 메세지인 경우
        if (message.isEmpty()){
            showToastMessage("내용을 작성해주세요.")
            return
        }

        // 서버에 보내기


        // 성공적으로 보내졌으면 리사이클러 뷰에 추가하기


    }


}