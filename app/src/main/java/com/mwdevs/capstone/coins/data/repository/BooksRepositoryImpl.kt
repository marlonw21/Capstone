package com.mwdevs.capstone.coins.data.repository

import com.mwdevs.capstone.coins.data.local.BooksDao
import com.mwdevs.capstone.coins.data.local.BooksDatabase
import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: BitsoServices,
    private val booksDao: BooksDao,
    private val dispatcher: CoroutineDispatcher
) : RetrofitInstance(), BooksRepository {

    override suspend fun getBooks(): ResponseHandler<List<BooksEntity>> = withContext(dispatcher){
        when (val response = callService { api.getAvailableBooks() }) {
            is ResponseHandler.Success -> {
                response.data?.let { bookList ->
                    val booksEntities = bookList.map { it.toEntityModel() }
                    booksDao.insertBooks(booksEntities)
                }
                ResponseHandler.Success(booksDao.getBooks())
            }
            is ResponseHandler.Error -> {
                val bookList = booksDao.getBooks()
                ResponseHandler.Error(
                    data = bookList.ifEmpty { null },
                    errorMsg = response.errorMsg
                )
            }
        }
    }

    override suspend fun getTicker(book: String): ResponseHandler<TickerResponseDTO> =
        withContext(dispatcher) {
            callService { api.getTicker(book) }
        }

    override suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO> =
        withContext(dispatcher) {
            callService { api.getBookDetail(book) }
        }

}