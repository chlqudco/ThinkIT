package com.chlqudco.develop.thinkit.data.entity

import com.google.gson.annotations.SerializedName

data class SignUpEntity(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("nickname") val nickname: String
)