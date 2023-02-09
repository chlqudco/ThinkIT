package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @SerializedName("result") val result: String,
    @SerializedName("accessToken") val token: String,
    @SerializedName("nickname") val nickname: String
)