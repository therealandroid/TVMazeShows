package com.diogojayme.tvmazeshows.model.view

import java.io.Serializable


data class Rating(
    val average: Float? = null,
): Serializable

data class ShowImage(
    val medium: String,
    val original: String,
) : Serializable

data class Show(
    val id: Long,
    val image: ShowImage? = null,
    val name: String,
    val summary: String? = null,
    val ended: String? = null,
    val rating: Rating
) : Serializable