package com.mwdevs.capstone.coins.data.repository

import com.google.gson.JsonSyntaxException
import com.mwdevs.capstone.coins.data.local.BooksDao
import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.coins.utils.CapstoneSchedulers
import com.mwdevs.capstone.coins.utils.toEntityModel
import com.mwdevs.capstone.utils.models.ResponseHandler
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: BitsoServices,
    private val booksDao: BooksDao,
    private val dispatcher: CoroutineDispatcher,
    private val scheduler: CapstoneSchedulers
) : BooksRepository {

    override suspend fun getBooks(): ResponseHandler<List<BooksEntity>> = withContext(dispatcher) {
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

    override fun getTicker(book: String): Observable<ResponseHandler<TickerResponseDTO>> {
        val response = api.getTicker(book)
        return response.map {
            it.successBody?.let { tickerResponse ->
                ResponseHandler.Success(data = tickerResponse)
            } ?: ResponseHandler.Error(errorMsg = it.errorBody?.message)
        }.subscribeOn(scheduler.io)
    }

    override suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO> =
        withContext(dispatcher) {
            callService { api.getBookDetail(book) }
        }

    private inline fun <T> callService(
        cb: () -> ResponseModel<T>
    ): ResponseHandler<T> {
        return try {
            val response = cb.invoke()
            response.let {
                if (it.success && it.successBody != null)
                    ResponseHandler.Success(
                        it.successBody!!
                    )
                else
                    ResponseHandler.Error(
                        data = null,
                        errorMsg = response.errorBody?.message
                    )
            }
        } catch (e: Exception) {
            ResponseHandler.Error(
                errorMsg = e.message
            )
        } catch (e: HttpException) {
            ResponseHandler.Error(
                errorMsg = e.message
            )
        } catch (e: JsonSyntaxException) {
            ResponseHandler.Error(
                errorMsg = e.message
            )
        } catch (e: UnknownHostException) {
            ResponseHandler.Error(
                errorMsg = e.message
            )
        }
    }
}
