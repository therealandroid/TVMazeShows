package com.diogojayme.tvmazeshows.activity.shows

import com.diogojayme.tvmazeshows.model.view.Show

/**
 * This is a similar approach to StateFlows and SharedFlows but for Rx
 *  https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=pt-br
 */
data class ShowsViewState(val contentType: ContentType) {

    sealed class ContentType {

        data class ListShows(val shows: List<Show>) : ContentType()

        object ListShowsError : ContentType()
    }
}

