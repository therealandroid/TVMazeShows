package com.diogojayme.tvmazeshows.activity.shows

import com.diogojayme.tvmazeshows.common.rx.addToCompositeDisposable
import com.diogojayme.tvmazeshows.common.viewmodel.LifeCycleViewModel
import com.diogojayme.tvmazeshows.repository.ShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

const val EMPTY_QUERY = "girls"

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val showsRepository: ShowsRepository,
) : LifeCycleViewModel() {

    val rendererObserver: PublishSubject<ShowsViewState> = PublishSubject.create()

    fun firstSearch() {
        searchShows(EMPTY_QUERY)
    }

    fun searchShows(query: String) {
        showsRepository.searchShows(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    rendererObserver.onNext(
                        ShowsViewState(
                            ShowsViewState.ContentType.ListShows(it)
                        )
                    )
                },
                {
                    rendererObserver.onNext(
                        ShowsViewState(
                            ShowsViewState.ContentType.ListShowsError
                        )
                    )
                }
            )
            .addToCompositeDisposable(disposable)
    }
}