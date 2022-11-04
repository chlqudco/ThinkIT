package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class KeywordsResponse(
    @SerializedName("keywords") val keywordsList: List<String>,
    @SerializedName("version") val version: Int
)