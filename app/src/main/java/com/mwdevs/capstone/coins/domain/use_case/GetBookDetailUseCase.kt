package com.mwdevs.capstone.coins.domain.use_case

import android.util.Log
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponse
import com.mwdevs.capstone.utils.APIServiceBuilder
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import okhttp3.ResponseBody

class GetBookDetailUseCase {
    val repo = APIServiceBuilder()
    suspend operator fun invoke(book: String): ResponseHandler<BookDetailsResponse?>{
        Log.e("boooook", book)
        return repo.getTest2(book)
    }
}