package com.masliaiev.gravitytest.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masliaiev.gravitytest.domain.usecases.LoadResponseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadFragmentViewModel @Inject constructor(
    private val loadResponseUseCase: LoadResponseUseCase
) : ViewModel() {

    private var _loadResult = MutableLiveData<Boolean>()
    val loadResult: LiveData<Boolean>
        get() = _loadResult

    init {
        loadResponse()
    }

    private fun loadResponse() {
        viewModelScope.launch {
            val loadIsSuccessful = loadResponseUseCase.loadResponse()
            _loadResult.value = loadIsSuccessful
        }
    }
}