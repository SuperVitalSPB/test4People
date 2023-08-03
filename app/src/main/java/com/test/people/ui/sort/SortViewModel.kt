package com.test.people.ui.sort

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SortViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sort Fragment"
    }
    val text: LiveData<String> = _text
}