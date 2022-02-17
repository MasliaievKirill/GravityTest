package com.masliaiev.gravitytest.domain.usecases

import com.masliaiev.gravitytest.domain.repository.ResponseRepository
import javax.inject.Inject

class CheckResponseIsExistUseCase @Inject constructor(
    private val repository: ResponseRepository
) {
    suspend fun responseIsExist(): Boolean {
       return repository.responseIsExist() == RESPONSE_IS_EXIST
    }

    companion object {
        private const val RESPONSE_IS_EXIST = 1
    }
}