package com.test.people.ui.sort

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.people.api.ApiResult
import com.test.people.interactor.InteractorSort
import com.test.people.model.Valute
import com.test.people.ui.SourceFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortViewModel @Inject constructor(private val interactorSort: InteractorSort) : ViewModel() {

    private val _valutes = MutableStateFlow(ApiResult.startValute())
    val valutes: StateFlow<ApiResult<List<Valute>>> = _valutes

    fun loadListRates(sourceFragment: SourceFragment) {
        viewModelScope.launch(Dispatchers.IO) {
            _valutes.value = interactorSort.getData(sourceFragment)
        }
    }

}