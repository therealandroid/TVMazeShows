package com.diogojayme.tvmazeshows.activity.detail

import com.diogojayme.tvmazeshows.common.rx.addToCompositeDisposable
import com.diogojayme.tvmazeshows.common.viewmodel.LifeCycleViewModel
import com.diogojayme.tvmazeshows.repository.ShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

const val ALIAS_PREFIX = "AKA's:"
const val ALIAS_SEPARATOR = ", "

//TODO: maybe use a shared viewmodel instead
@HiltViewModel
class ShowsDetailViewModel @Inject constructor(
    private val showsRepository: ShowsRepository,
) : LifeCycleViewModel() {

    val rendererObserver: PublishSubject<ShowDetailsViewState> = PublishSubject.create()

    fun loadAlias(showId: Long) {
        showsRepository.aliases(showId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { aliasesList ->
                    if (aliasesList.isEmpty()) {
                        rendererObserver.onNext(ShowDetailsViewState(ShowDetailsViewState.ContentType.EmptyAliases))
                    } else {

                        val alias = aliasesList.joinToString(
                            prefix = ALIAS_PREFIX,
                            separator = ALIAS_SEPARATOR
                        ) { it.name }

                        rendererObserver.onNext(
                            ShowDetailsViewState(
                                ShowDetailsViewState.ContentType.LoadAliases(alias)
                            )
                        )
                    }
                },
                {
                    // send some analytics log
                    rendererObserver.onNext(
                        ShowDetailsViewState(ShowDetailsViewState.ContentType.EmptyAliases)
                    )

                }
            )
            .addToCompositeDisposable(disposable)
    }
}