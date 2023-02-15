package com.mwdevs.capstone.coins.domain.use_case

import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(private val repository: BooksRepository) {

    suspend operator fun invoke(book: String): ResponseHandler<TickerResponseDTO> = repository.getTicker(book)
}