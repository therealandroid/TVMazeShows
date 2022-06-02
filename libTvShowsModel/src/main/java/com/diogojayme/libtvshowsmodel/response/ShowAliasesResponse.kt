package com.diogojayme.libtvshowsmodel.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowAliasesResponse(
    val name: String
)