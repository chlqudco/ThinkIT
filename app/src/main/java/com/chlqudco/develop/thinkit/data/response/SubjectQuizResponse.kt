package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class SubjectQuizResponse(
    @SerializedName("problems") val problems: List<String>,
)