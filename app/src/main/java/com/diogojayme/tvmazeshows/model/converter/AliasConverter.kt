package com.diogojayme.tvmazeshows.model.converter

import com.diogojayme.libtvshowsmodel.response.ShowAliasesResponse
import com.diogojayme.libtvshowsmodel.response.ShowResponse
import com.diogojayme.libtvshowsmodel.response.ShowResponseWrapper
import com.diogojayme.tvmazeshows.model.view.Show
import com.diogojayme.tvmazeshows.model.view.ShowAliases
import com.diogojayme.tvmazeshows.model.view.ShowImage


fun List<ShowAliasesResponse>.convert(): List<ShowAliases> = map {
    it.convert()
}

fun ShowAliasesResponse.convert() = ShowAliases(
    name = name,
)
