package com.diogojayme.tvmazeshows.repository

import com.diogojayme.libtvshowsmodel.response.RatingResponse
import com.diogojayme.libtvshowsmodel.response.ShowImageResponse
import com.diogojayme.libtvshowsmodel.response.ShowResponse
import com.diogojayme.libtvshowsmodel.response.ShowResponseWrapper
import com.diogojayme.tvmazeshows.model.view.Rating
import com.diogojayme.tvmazeshows.model.view.Show
import com.diogojayme.tvmazeshows.model.view.ShowImage
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//TODO replace by AndroidJUnit4
@RunWith(JUnit4::class)
class ShowsRepositoryTest {

    @MockK
    private lateinit var mockApi: ShowsApi
    private lateinit var showsRepository: ShowsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // schedulers not necessary in repository since it don't handle subscription and observer threads
        showsRepository = ShowsRepository(mockApi)
    }

    @Test
    fun `Search shows should map showResponse to show`() {
        // Given
        val input = ShowResponseWrapper(
            0.0,
            ShowResponse(
                1,
                ShowImageResponse("mock", "mock"),
                "mock",
                "mock",
                "mock",
                RatingResponse(
                    1.0F
                )
            )
        )

        val output = Show(
            id = 1,
            image = ShowImage("mock", "mock"),
            name = "mock",
            summary = "mock",
            ended = "mock",
            rating = Rating(
                1.0F
            )
        )
        every { mockApi.searchShows(any()) } returns Observable.just(listOf(input))

        // When
        val observableTest = showsRepository.searchShows("").test()

        // Then
        observableTest.assertValue(listOf(output))
    }

    //TODO: Test other scenarios, like non-required and required fields
}