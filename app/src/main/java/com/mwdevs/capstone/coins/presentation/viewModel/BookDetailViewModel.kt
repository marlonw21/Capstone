package com.mwdevs.capstone.coins.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.model.AskBidsModel
import com.mwdevs.capstone.coins.domain.use_case.GetBookDetailUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetTickerUseCase
import com.mwdevs.capstone.utils.models.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getTicker: GetTickerUseCase,
    private val getBookDetail: GetBookDetailUseCase
): ViewModel() {

    val d = CompositeDisposable()

    private val _asksModel = MutableLiveData<List<AskBidsModel>?>()
        val askModel: LiveData<List<AskBidsModel>?> = _asksModel
    private val _bidsModel = MutableLiveData<List<AskBidsModel>?>()
        val bidsModel: LiveData<List<AskBidsModel>?> = _bidsModel
    private val _tickerModel = MutableLiveData<ResponseHandler<TickerResponseDTO>>() //TODO falta el model de UI
        val tickerModel: LiveData<ResponseHandler<TickerResponseDTO>> = _tickerModel
    private val _errorHandler = MutableLiveData<String?>()
        val errorHandler: LiveData<String?> = _errorHandler

    fun getTicker(book: String){
        getTicker.invoke(book)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(getTickerObserverRx())
    }



    private fun getTickerObserverRx(): Observer<ResponseModel<TickerResponseDTO>> =
        object : Observer<ResponseModel<TickerResponseDTO>>{
            override fun onSubscribe(d: Disposable) {
                Log.e("onSubscribe", "$d")
            }

            override fun onNext(t: ResponseModel<TickerResponseDTO>) {
                t.successBody?.let {
                    _tickerModel.postValue(ResponseHandler.Success(it))
                }
            }

            override fun onError(e: Throwable) {
                _errorHandler.postValue(e.message)
            }

            override fun onComplete() {
                Log.e("onComplete", "")
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

