package com.masliaiev.gravitytest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response")
data class ResponseDbModel(
    val link: String,
    @PrimaryKey
    val home: String
)