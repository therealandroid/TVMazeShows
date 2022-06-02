package com.diogojayme.libtvshowsmodel.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowResponseWrapper(
    val score: Double,
    val show: ShowResponse,
)