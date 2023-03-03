package com.mwdevs.capstone.coins.domain.use_case

import com.mwdevs.capstone.coins.domain.model.AskBidsModel
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.models.ResponseHandler
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(private val repository: BooksRepository) {

    suspend operator fun invoke(book: String): ResponseHandler<Pair<List<AskBidsModel>?, List<AskBidsModel>?>> {
        return when (val response = repository.getBookDetail(book)) {
            is ResponseHandler.Success -> {
                ResponseHandler.Success(
                    Pair(
                        response.data?.asksToUIModel(),
                        response.data?.bidsToUIModel()
                    )
                )
            }
            is ResponseHandler.Error -> {
                ResponseHandler.Error(
                    errorMsg = response.errorMsg
                )
            }
        }
    }
}
