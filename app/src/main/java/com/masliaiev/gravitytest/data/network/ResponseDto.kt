package com.masliaiev.gravitytest.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("link")
    @Expose
    val link: String,
    @SerializedName("home")
    @Expose
    val home: String
)