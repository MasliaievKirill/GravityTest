package com.masliaiev.gravitytest.presentation.view_models

import androidx.lifecycle.ViewModel
import com.masliaiev.gravitytest.domain.usecases.GetResponseUseCase
import javax.inject.Inject

class WebViewFragmentViewModel @Inject constructor(
    private val getResponseUseCase: GetResponseUseCase
) : ViewModel() {

    val response = getResponseUseCase.getResponse()

}