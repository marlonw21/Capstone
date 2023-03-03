package com.mwdevs.capstone.coins.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.models.ResponseHandler
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class GetBookListUseCaseTest {

    @RelaxedMockK
    private lateinit var booksRepository: BooksRepository

    private lateinit var getBookListUseCase: GetBookListUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBookListUseCase = GetBookListUseCase(booksRepository)
    }

    @Test
    fun `get book list from success response from api`() = runTest {
        // Given
        val listSuccess = ResponseHandler.Success(data = getBooksEntities())
        coEvery { booksRepository.getBooks() } returns listSuccess

        // When
        val response = booksRepository.getBooks()

        // Then
        assertThat(response).isEqualTo(listSuccess)
    }

    @Test
    fun `get error response with null data from database`() = runTest {
        // Given
        val errorResponseWithNullData = ResponseHandler.Error(
            data = emptyList<BooksEntity>(),
            errorMsg = "An error ocurred"
        )
        coEvery { booksRepository.getBooks() } returns errorResponseWithNullData

        // When
        val response = booksRepository.getBooks()

        // Then
        assert(response.data.isNullOrEmpty())
    }

    @Test
    fun `get error response with data from database`() = runTest {
        // Given
        val errorResponseWithNullData = ResponseHandler.Error(
            data = getBooksEntities(),
            errorMsg = "An error ocurred"
        )
        coEvery { booksRepository.getBooks() } returns errorResponseWithNullData

        // When
        val response = booksRepository.getBooks()

        // Then
        assert(response.data == getBooksEntities())
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    private fun getBooksEntities(): List<BooksEntity> = listOf(
        BooksEntity(
            book = "",
            coinName = "",
            maximum_value = "",
            maximum_price = "",
            maximum_amount = "",
            minimum_amount = "",
            minimum_value = "",
            minimum_price = ""
        ),
        BooksEntity(
            book = "",
            coinName = "",
            maximum_value = "",
            maximum_price = "",
            maximum_amount = "",
            minimum_amount = "",
            minimum_value = "",
            minimum_price = ""
        ),
        BooksEntity(
            book = "",
            coinName = "",
            maximum_value = "",
            maximum_price = "",
            maximum_amount = "",
            minimum_amount = "",
            minimum_value = "",
            minimum_price = ""
        )
    )
}
