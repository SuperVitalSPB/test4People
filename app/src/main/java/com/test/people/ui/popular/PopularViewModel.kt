package com.test.people.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.people.api.ApiResult
import com.test.people.di.InteractorEntity
import com.test.people.di.NetworkUtils
import com.test.people.model.ErrorResponse
import com.test.people.model.LatestRate
import com.test.people.model.LatestRateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel(var interactorEntity: InteractorEntity) : ViewModel() {

    var rates = MutableLiveData<ApiResult<LatestRate>>()

    init {
        loadListRates()
    }

    fun loadListRates() {
        viewModelScope.launch(Dispatchers.IO) {
            rates.postValue(interactorEntity.getLatest())
        }
    }
}