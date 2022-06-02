package com.diogojayme.tvmazeshows.repository

import com.diogojayme.libtvshowsmodel.response.ShowAliasesResponse
import com.diogojayme.libtvshowsmodel.response.ShowResponseWrapper
import com.diogojayme.tvmazeshows.model.view.ShowAliases
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsApi {

    @GET("/search/shows")
    fun searchShows(@Query("q") query: String): Observable<List<ShowResponseWrapper>>

    @GET("/shows/{id}/akas")
    fun showAliases(@Path("id") id: Long): Observable<List<ShowAliasesResponse>>

}