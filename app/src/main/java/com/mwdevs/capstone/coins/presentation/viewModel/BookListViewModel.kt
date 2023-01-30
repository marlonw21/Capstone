package com.mwdevs.capstone.coins.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.utils.APIServiceBuilder
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {
    val repository = APIServiceBuilder()

    private val _bookList = MutableLiveData<ResponseHandler<List<Payload>?>>()
    val bookList:LiveData<ResponseHandler<List<Payload>?>> = _bookList

    fun getBooks() {
        viewModelScope.launch {
            when(val response = repository.getTest()){
                is ResponseHandler.Error -> _bookList.postValue(response)
                is ResponseHandler.Success -> _bookList.postValue(response)

            }
        }
    }
}