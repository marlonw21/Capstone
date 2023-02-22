package com.mwdevs.capstone.coins.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.coins.domain.use_case.GetBookListUseCase
import com.mwdevs.capstone.utils.models.ResponseHandler
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BookListViewModelTest{

    @RelaxedMockK
    private lateinit var getBookListUseCase: GetBookListUseCase

    @RelaxedMockK
    private lateinit var bookListViewModel: BookListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        bookListViewModel = BookListViewModel(getBookListUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `when getBook method is called, get the list of books from database`() = runTest{
        //Given
        val booksList = listOf(CoinUIModel(
             id = "",
            coinResource = mapOf(0 to listOf(0)),
            )
        )
        coEvery { getBookListUseCase() } returns ResponseHandler.Success(booksList)

        //When
        bookListViewModel.getBooks()


        //Then
        assert(bookListViewModel.bookList.value?.data == booksList)
        assert(bookListViewModel.bookList.value is ResponseHandler.Success)
    }

    @Test
    fun `when getBooks method return an Error, verify if data is not null`(){
        //Given
        val booksList = listOf(CoinUIModel(
            id = "",
            coinResource = mapOf(0 to listOf(0)),
        )
        )
        coEvery { getBookListUseCase() } returns ResponseHandler.Error(
            data = booksList,
            errorMsg = "Error msg"
        )

        //When
        bookListViewModel.getBooks()

        //Then
        assert(bookListViewModel.bookList.value?.data?.isNotEmpty() == true)
        assert(bookListViewModel.bookList.value is ResponseHandler.Error)
    }

    @Test
    fun `when getBooks method return an Error, verify if data is null`(){
        //Given
        coEvery { getBookListUseCase() } returns ResponseHandler.Error(
            data = null,
            errorMsg = "Error msg"
        )

        //When
        bookListViewModel.getBooks()

        //Then
        assert(bookListViewModel.bookList.value?.data == null)
        assert(bookListViewModel.bookList.value is ResponseHandler.Error)
    }
}