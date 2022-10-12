package com.chlqudco.develop.thinkit.data.response

import com.google.gson.annotations.SerializedName

data class MultipleChoiceQuizResponse(
    @SerializedName("problems") val problems: List<String>,
    @SerializedName("selects") val selects: List<String>
)
