package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @SerializedName("result") val result: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("nickname") val nickname: String
)