package com.diogojayme.tvmazeshows.activity.detail

/**
 * This is a similar approach to StateFlows and SharedFlows but for Rx
 *  https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=pt-br
 */
data class ShowDetailsViewState(val contentType: ContentType) {

    sealed class ContentType {
        data class LoadAliases(val aliases: String) : ContentType()
        object EmptyAliases: ContentType()
    }
}
