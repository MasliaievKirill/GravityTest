package com.masliaiev.gravitytest.domain.usecases

import com.masliaiev.gravitytest.domain.repository.ResponseRepository
import javax.inject.Inject

class LoadResponseUseCase @Inject constructor(
    private val repository: ResponseRepository
) {
    suspend fun loadResponse() {
        repository.loadResponse()
    }
}