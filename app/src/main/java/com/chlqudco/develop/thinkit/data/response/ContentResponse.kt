package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class ContentResponse(
    @SerializedName("keyword_content") val content : String
) {
}