package com.mwdevs.capstone.coins.data.repository

import com.mwdevs.capstone.coins.data.local.BooksDatabase
import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: BitsoServices,
    private val db: BooksDatabase
) : RetrofitInstance(), BooksRepository {
    private val dao = db.dao

    override suspend fun getBooks(): ResponseHandler<List<BooksEntity>> =
        when (val response = callService { api.getAvailableBooks() }) {
            is ResponseHandler.Success -> {
                response.data?.let { bookList ->
                    val booksEntities = bookList.map { it.toEntityModel() }
                    dao.insertBooks(booksEntities)
                }
                ResponseHandler.Success(dao.getBooks())
            }
            is ResponseHandler.Error -> {
                val bookList = dao.getBooks()
                ResponseHandler.Error(
                    data = bookList.ifEmpty { null },
                    errorMsg = response.errorMsg
                )
            }
        }

    override suspend fun getTicker(book: String): ResponseHandler<TickerResponseDTO> =
        callService { api.getTicker(book) }

    override suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO> =
        callService { api.getBookDetail(book) }

}