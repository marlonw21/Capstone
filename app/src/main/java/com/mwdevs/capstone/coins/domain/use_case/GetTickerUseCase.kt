package com.mwdevs.capstone.coins.domain.use_case

import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.models.ResponseHandler
import io.reactivex.Observable
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(private val repository: BooksRepository) {

    operator fun invoke(book: String): Observable<ResponseHandler<TickerResponseDTO>> = repository.getTicker(book)
}
