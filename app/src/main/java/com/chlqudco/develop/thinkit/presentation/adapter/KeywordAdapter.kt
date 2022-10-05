package com.chlqudco.develop.thinkit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.thinkit.databinding.ItemKeywordBinding

class KeywordAdapter: RecyclerView.Adapter<KeywordAdapter.ViewHolder>() {

    var keywordList : List<String> = emptyList()

    inner class ViewHolder(private val binding: ItemKeywordBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(keyword: String){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(keywordList[position])
    }

    override fun getItemCount(): Int {
        return keywordList.size
    }

}