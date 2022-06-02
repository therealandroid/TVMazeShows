package com.diogojayme.tvmazeshows.viewmodel

import com.diogojayme.tvmazeshows.activity.detail.ALIAS_PREFIX
import com.diogojayme.tvmazeshows.activity.detail.ShowDetailsViewState
import com.diogojayme.tvmazeshows.activity.detail.ShowsDetailViewModel
import com.diogojayme.tvmazeshows.model.view.ShowAliases
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
class ShowsDetailViewModelTest {

    @MockK(relaxed = true)
    private lateinit var mockRepository: ShowsRepository

    private lateinit var viewModelUnderTest: ShowsDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModelUnderTest = ShowsDetailViewModel(mockRepository)
    }

    @Test
    fun `when get aliases successfully - with two aliases - should return LoadAliases state with alias prefix and comma separator`() {
        // Given
        val mockAlias1 = "alias1"
        val mockAlias2 = "alias2"
        val expectedResult = "$ALIAS_PREFIX$mockAlias1, $mockAlias2"

        every { mockRepository.aliases(any()) } returns Observable.just(
            listOf(
                ShowAliases(
                    mockAlias1
                ),
                ShowAliases(
                    mockAlias2,
                )
            )
        )

        // When
        val testObserver = viewModelUnderTest.rendererObserver.test()
        viewModelUnderTest.loadAlias(1)

        // Then
        testObserver.assertValue(
            ShowDetailsViewState(
                ShowDetailsViewState.ContentType.LoadAliases(
                    expectedResult
                )
            )
        )
        testObserver.dispose()
    }

    @Test
    fun `when get aliases successfully - should return LoadAliases state with alias prefix`() {
        // Given
        val givenAlias = "mock"
        val expectedResult = ALIAS_PREFIX + givenAlias

        every { mockRepository.aliases(any()) } returns Observable.just(
            listOf(
                ShowAliases(
                    givenAlias
                )
            )
        )

        // When
        val testObserver = viewModelUnderTest.rendererObserver.test()
        viewModelUnderTest.loadAlias(1)

        // Then
        testObserver.assertValue(
            ShowDetailsViewState(
                ShowDetailsViewState.ContentType.LoadAliases(
                    expectedResult
                )
            )
        )
        testObserver.dispose()
    }

    @Test
    fun `when get aliases successfully - with empty return - should return empty state`() {
        // Given
        every { mockRepository.aliases(any()) } returns Observable.just(listOf())

        // When
        val testObserver = viewModelUnderTest.rendererObserver.test()
        viewModelUnderTest.loadAlias(1)

        // Then
        testObserver.assertValue(
            ShowDetailsViewState(
                ShowDetailsViewState.ContentType.EmptyAliases
            )
        )
        testObserver.dispose()
    }

}