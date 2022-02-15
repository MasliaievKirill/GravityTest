package com.masliaiev.gravitytest.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.gravitytest.domain.entity.Response
import com.masliaiev.gravitytest.domain.repository.ResponseRepository
import javax.inject.Inject

class GetResponseUseCase @Inject constructor(
    private val repository: ResponseRepository
) {
    fun getResponse(): LiveData<List<Response>>{
        return repository.getResponse()
    }
}