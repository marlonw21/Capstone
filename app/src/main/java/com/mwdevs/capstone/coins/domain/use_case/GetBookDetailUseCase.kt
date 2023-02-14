package com.mwdevs.capstone.coins.domain.use_case

import com.mwdevs.capstone.coins.domain.model.AskBidsModel
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class GetBookDetailUseCase(private val repository: BooksRepository) {

    suspend operator fun invoke(book: String): ResponseHandler<Pair<List<AskBidsModel>?, List<AskBidsModel>?>>{
        val response = repository.getBookDetail(book)
        return when (response){
            is ResponseHandler.Success ->{
                ResponseHandler.Success(
//                    data = ResponseModel(
//                        success = response.data?.success == true,
                       Pair(
                            response.data?.asksToUIModel(),
                            response.data?.bidsToUIModel()
                        )
//                    )
                )
            }
            is ResponseHandler.Error ->{
               ResponseHandler.Error(
                   errorMsg = response.errorMsg
//                   data = ResponseModel(
//                       success = response.data?.success ?: false,
//                       errorBody = response.data?.errorBody,
//                       successBody = null
//                   ),
//                   errorBody = null
               )
            }
        }
    }
}