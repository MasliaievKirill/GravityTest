package com.masliaiev.gravitytest.data.mapper

import com.masliaiev.gravitytest.data.database.ResponseDbModel
import com.masliaiev.gravitytest.data.network.ResponseDto
import com.masliaiev.gravitytest.domain.entity.Response
import javax.inject.Inject

class ResponseMapper @Inject constructor() {

    fun mapResponseDtoToResponseDbModel(responseDto: ResponseDto): ResponseDbModel{
        return ResponseDbModel(
            link = responseDto.link,
            home = responseDto.home,
            id = RESPONSE_ID
        )
    }

    fun mapResponseDbModelToResponseEntity(responseDbModel: ResponseDbModel): Response{
        return Response(
            link = responseDbModel.link,
            home = responseDbModel.home
        )
    }

    companion object{
        private const val RESPONSE_ID = 1
    }

}