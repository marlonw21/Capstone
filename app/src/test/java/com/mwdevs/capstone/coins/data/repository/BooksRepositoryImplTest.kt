package com.mwdevs.capstone.coins.data.repository

import com.mwdevs.capstone.coins.data.local.BooksDao
import com.mwdevs.capstone.coins.data.local.BooksDatabase
import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDTO
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BooksRepositoryImplTest{

    private lateinit var sut: BooksRepositoryImpl

    @RelaxedMockK
    private lateinit var booksDao: BooksDao

    private lateinit var dispatcher: CoroutineDispatcher

    @RelaxedMockK
    private lateinit var booksServices: BitsoServices



    @Before
    fun onBefore(){
        dispatcher = UnconfinedTestDispatcher()
        MockKAnnotations.init(this)
        sut = BooksRepositoryImpl(
            booksServices,
            booksDao,
            dispatcher
        )
    }

    @Test
    fun `when api get books successfully and successfully stored in database return book entities from database`()= runTest{
        //Given
        val apiResponse = ResponseModel(
            success = true,
            successBody = listOf(BookDTO(
                book = "",
                coinName = "",
                maximum_amount = "",
                maximum_value = "",
                maximum_price = "",
                minimum_amount = "",
                minimum_price = "",
                minimum_value = ""
            ))
        )
        val valuesFromDB = listOf(BooksEntity(
            book = "",
            coinName = "",
            maximum_amount = "",
            maximum_value = "",
            maximum_price = "",
            minimum_amount = "",
            minimum_price = "",
            minimum_value = ""
        ))
        coEvery { booksServices.getAvailableBooks() } returns apiResponse
        coEvery { booksDao.getBooks() } returns valuesFromDB

        //When
        val response = sut.getBooks()

        //Then
        assert(response.data == valuesFromDB)
        assert(response is ResponseHandler.Success)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }
}