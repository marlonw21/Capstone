package com.mwdevs.capstone.coins.domain.use_case

import com.mwdevs.capstone.coins.data.remote.model.TickerResponse
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class GetTickerUseCase(private val repository: BooksRepository) {

    suspend operator fun invoke(book: String): ResponseHandler<TickerResponse?> = repository.getTicker(book)
}