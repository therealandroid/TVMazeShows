package com.diogojayme.tvmazeshows.repository

import com.diogojayme.tvmazeshows.model.converter.convert
import com.diogojayme.tvmazeshows.model.view.Show
import com.diogojayme.tvmazeshows.model.view.ShowAliases
import io.reactivex.rxjava3.core.Observable

class ShowsRepository(
    private val showsApi: ShowsApi,
) {

    fun searchShows(query: String): Observable<List<Show>> =
        showsApi.searchShows(query).map {
            it.convert()
        }

    fun aliases(showId: Long): Observable<List<ShowAliases>> =
        showsApi.showAliases(showId).map {
            it.convert()
        }
}