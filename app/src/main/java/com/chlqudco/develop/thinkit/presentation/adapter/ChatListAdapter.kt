package com.chlqudco.develop.thinkit.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.data.model.Chat

class ChatListAdapter : ListAdapter<Chat, RecyclerView.ViewHolder>(ChatDiffCallback) {

    inner class ChatMyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(chat: Chat) {
            val messageTextView = view.findViewById<TextView>(R.id.text_gchat_message_me)

            messageTextView.text = chat.message
        }
    }

    inner class ChatYouViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(chat: Chat) {
            val messageTextView = view.findViewById<TextView>(R.id.text_gchat_message_other)

            messageTextView.text = chat.message

        }
    }

    companion object {
        private val ChatDiffCallback = object : DiffUtil.ItemCallback<Chat>() {
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                // 바꿔야 됨
                return oldItem.message == newItem.message
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ChatMyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_chat_my, parent, false)
            )
            1 -> ChatYouViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_chat_you, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = currentList[position]
        when (holder.itemViewType) {
            0 -> {
                (holder as ChatMyViewHolder).bind(chat)
            }
            1 -> {
                (holder as ChatYouViewHolder).bind(chat)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return if(item.myChat) 0 else 1
    }

}