package com.test.people.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.people.api.ApiResult
import com.test.people.interactor.InteractorEntity
import com.test.people.model.LatestRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularViewModel(val interactorEntity: InteractorEntity) : ViewModel() {

    private val _rates = MutableStateFlow(ApiResult.startValue())
    val rates: StateFlow<ApiResult<LatestRate>> = _rates

    fun loadListRates() {
        viewModelScope.launch(Dispatchers.IO) {
            _rates.value = interactorEntity.getLatest()
        }
    }

}