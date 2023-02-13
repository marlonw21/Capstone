package com.mwdevs.capstone.coins.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdevs.capstone.coins.data.repository.BooksRepositoryImpl
import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.coins.domain.use_case.GetBookListUseCase
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.launch

class BookListViewModel(
    private val getBooks: GetBookListUseCase = GetBookListUseCase(BooksRepositoryImpl())
) : ViewModel() {

    private val _bookList = MutableLiveData<ResponseHandler<List<CoinUIModel>?>>()
    val bookList:LiveData<ResponseHandler<List<CoinUIModel>?>> = _bookList

    fun getBooks() {
        viewModelScope.launch {
            when(val response = getBooks.invoke()){
                is ResponseHandler.Error -> _bookList.postValue(response)
                is ResponseHandler.Success -> _bookList.postValue(response)
            }
        }
    }
}