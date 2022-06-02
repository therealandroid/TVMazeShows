package com.diogojayme.tvmazeshows.model.converter

import com.diogojayme.libtvshowsmodel.response.ShowResponse
import com.diogojayme.libtvshowsmodel.response.ShowResponseWrapper
import com.diogojayme.tvmazeshows.model.view.Rating
import com.diogojayme.tvmazeshows.model.view.Show
import com.diogojayme.tvmazeshows.model.view.ShowImage


fun List<ShowResponseWrapper>.convert(): List<Show> = map {
    it.show.convert()
}

fun ShowResponse.convert() = Show(
    id = id,
    image = showImageResponse?.let {
        ShowImage(
            medium = it.medium,
            original = it.original,
        )
    },
    rating = Rating(
        average = rating.average
    ),
    summary = summary,
    ended = ended,
    name = name,
)
