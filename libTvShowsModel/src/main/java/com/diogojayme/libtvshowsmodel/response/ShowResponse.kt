package com.diogojayme.libtvshowsmodel.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatingResponse(
    val average: Float? = null,
)

@JsonClass(generateAdapter = true)
data class ShowImageResponse(
    val medium: String,
    val original: String,
)

@JsonClass(generateAdapter = true)
data class ShowResponse(
    val id: Long,
    @Json(name = "image")
    val showImageResponse: ShowImageResponse? = null,
    val name: String,
    val summary: String? = null,
    val ended: String? = null,
    val rating: RatingResponse
)