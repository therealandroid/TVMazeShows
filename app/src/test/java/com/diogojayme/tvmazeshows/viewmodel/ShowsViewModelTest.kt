package com.diogojayme.tvmazeshows.viewmodel

import com.diogojayme.tvmazeshows.activity.shows.ShowsViewModel
import com.diogojayme.tvmazeshows.activity.shows.ShowsViewState
import com.diogojayme.tvmazeshows.repository.ShowsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


//TODO replace by AndroidJUnit4
@RunWith(JUnit4::class)
class ShowsViewModelTest {

    @MockK(relaxed = true)
    private lateinit var mockRepository: ShowsRepository

    private lateinit var viewModelUnderTest: ShowsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModelUnderTest = ShowsViewModel(mockRepository)
    }

    @Test
    fun `search shows successfully should show ListShows state`() {
        // Given
        val query = ""
        every { mockRepository.searchShows(query) } returns Observable.just(listOf())

        // When
        val testObserver = viewModelUnderTest.rendererObserver.test()
        viewModelUnderTest.searchShows(query)

        // Then
        testObserver.assertValue(ShowsViewState(ShowsViewState.ContentType.ListShows(listOf())))
        testObserver.dispose()
    }

    @Test
    fun `search shows error should show ListShowsError state`() {
        // Given
        val query = ""
        every { mockRepository.searchShows(query) } returns Observable.error(Throwable())

        // When
        val testObserver = viewModelUnderTest
            .rendererObserver
            .test()

        viewModelUnderTest.searchShows(query)

        // Then
        testObserver.assertValue(ShowsViewState(ShowsViewState.ContentType.ListShowsError))
        testObserver.dispose()
    }

}