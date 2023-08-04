package com.test.people.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.people.api.ApiResult
import com.test.people.di.NetworkUtils
import com.test.people.model.ErrorResponse
import com.test.people.model.LatestRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel(var networkUtils: NetworkUtils) : ViewModel() {

    var rates = MutableLiveData<ApiResult<LatestRate>>()

    init {
        loadListRates()
    }

    fun loadListRates() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = networkUtils.retrofit.getLatest()
            val errMessage = result.errorBody()?.string()
            errMessage?.let {
                val errMessage = ErrorResponse().from(it)?.message ?: "Not response"
                rates.postValue(ApiResult.Error(errMessage))
                return@launch
            }
            rates.postValue(ApiResult.Success(result.body()))
        }
    }
}

// Override ViewModelProvider.NewInstanceFactory to create the ViewModel (VM).
class PopularViewModelFactory(val networkUtils: NetworkUtils): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = PopularViewModel(networkUtils) as T
}