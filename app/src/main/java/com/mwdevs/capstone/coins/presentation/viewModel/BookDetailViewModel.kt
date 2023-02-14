package com.mwdevs.capstone.coins.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.data.repository.BooksRepositoryImpl
import com.mwdevs.capstone.coins.domain.model.AskBidsModel
import com.mwdevs.capstone.coins.domain.use_case.GetBookDetailUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetTickerUseCase
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val getTicker: GetTickerUseCase = GetTickerUseCase(BooksRepositoryImpl()),
    private val getBookDetail: GetBookDetailUseCase = GetBookDetailUseCase(BooksRepositoryImpl())
): ViewModel() {

    private val _asksModel = MutableLiveData<List<AskBidsModel>?>()
        val askModel: LiveData<List<AskBidsModel>?> = _asksModel
    private val _bidsModel = MutableLiveData<List<AskBidsModel>?>()
        val bidsModel: LiveData<List<AskBidsModel>?> = _bidsModel
    private val _tickerModel = MutableLiveData<ResponseHandler<TickerResponseDTO>>() //TODO falta el model de UI
        val tickerModel: LiveData<ResponseHandler<TickerResponseDTO>> = _tickerModel
    private val _errorHandler = MutableLiveData<String?>()
        val errorHandler: LiveData<String?> = _errorHandler

    fun getTicker(book: String){
        viewModelScope.launch {
            when(val response = getTicker.invoke(book)){
                is ResponseHandler.Success -> _tickerModel.postValue(response)
                is ResponseHandler.Error ->  _errorHandler.postValue(response.errorMsg)
            }
        }
    }

    fun getBookDetail(book: String){
        viewModelScope.launch {
            when(val response = getBookDetail.invoke(book)){
                //First element always will be ASKS
                //Second element always will be BIDS
                is ResponseHandler.Success -> {
                    _asksModel.postValue(response.data?.first)
                    _bidsModel.postValue(response.data?.second)
                }
                is ResponseHandler.Error -> _errorHandler.postValue(response.errorMsg)
            }
        }
    }
}

