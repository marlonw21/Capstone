package com.mwdevs.capstone.coins.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdevs.capstone.coins.data.repository.BooksRepositoryImpl
import com.mwdevs.capstone.coins.domain.use_case.GetBookDetailUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetTickerUseCase
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val getTicker: GetTickerUseCase = GetTickerUseCase(BooksRepositoryImpl()),
    private val getBookDetail: GetBookDetailUseCase = GetBookDetailUseCase(BooksRepositoryImpl())
): ViewModel() {

    fun getTicker(book: String){
        viewModelScope.launch {
            when(val response = getTicker.invoke(book)){
                is ResponseHandler.Success -> Unit
                is ResponseHandler.Error -> Unit
            }
        }
    }

    fun getBookDetail(book: String){
        viewModelScope.launch {
            when(val response = getBookDetail.invoke(book)){
                is ResponseHandler.Success -> Unit
                is ResponseHandler.Error -> Unit
            }
        }
    }
}

