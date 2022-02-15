package com.masliaiev.gravitytest.domain.repository

import androidx.lifecycle.LiveData
import com.masliaiev.gravitytest.domain.entity.Response

interface ResponseRepository {

    suspend fun loadResponse()

    fun getResponse(): LiveData<List<Response>>

    suspend fun deleteResponse()

}