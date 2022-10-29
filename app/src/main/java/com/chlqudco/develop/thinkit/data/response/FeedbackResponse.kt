package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class FeedbackResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean
) {
}