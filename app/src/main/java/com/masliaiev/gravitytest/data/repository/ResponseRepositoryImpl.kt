package com.masliaiev.gravitytest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.masliaiev.gravitytest.data.database.ResponseDao
import com.masliaiev.gravitytest.data.mapper.ResponseMapper
import com.masliaiev.gravitytest.data.network.ApiService
import com.masliaiev.gravitytest.domain.entity.Response
import com.masliaiev.gravitytest.domain.repository.ResponseRepository
import javax.inject.Inject

class ResponseRepositoryImpl @Inject constructor(
    private val responseDao: ResponseDao,
    private val apiService: ApiService,
    private val mapper: ResponseMapper
) : ResponseRepository {

    override suspend fun loadResponse(): Boolean {
        return try {
            val response = apiService.loadResponse()
            responseDao.insertResponse(mapper.mapResponseDtoToResponseDbModel(response))
            true
        } catch (exception: Exception) {
            false
        }
    }

    override fun getResponse(): LiveData<Response> {
        return Transformations.map(responseDao.getResponse()) {
            mapper.mapResponseDbModelToResponseEntity(it)
        }
    }

    override suspend fun responseIsExist(): Int {
        return responseDao.responseIsExist()
    }
}