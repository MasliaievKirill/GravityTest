package com.masliaiev.gravitytest.data.network

import retrofit2.http.GET

interface ApiService {

    @GET("prod")
    suspend fun loadResponse(): ResponseDto

}