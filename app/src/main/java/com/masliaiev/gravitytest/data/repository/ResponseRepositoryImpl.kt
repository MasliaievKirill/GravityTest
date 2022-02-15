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

    override suspend fun loadResponse() {
        val response = apiService.loadResponse()
        responseDao.insertResponse(mapper.mapResponseDtoToResponseDbModel(response))
    }

    override fun getResponse(): LiveData<List<Response>> {
        return Transformations.map(responseDao.getResponse()) {
            it.map {
                mapper.mapResponseDbModelToResponseEntity(it)
            }
        }
    }

    override suspend fun deleteResponse() {
        responseDao.deleteResponse()
    }
}