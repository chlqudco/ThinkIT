package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class ChatRoomIdResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("roomId") val roomId: Long,
    @SerializedName("refreshToken") val refreshToken: String
) {
}