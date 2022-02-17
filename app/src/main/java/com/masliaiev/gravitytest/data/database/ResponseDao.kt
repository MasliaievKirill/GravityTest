package com.masliaiev.gravitytest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResponseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertResponse(response: ResponseDbModel)

    @Query("SELECT * FROM response WHERE id = 1 LIMIT 1")
    fun getResponse(): LiveData<ResponseDbModel>

    @Query("SELECT EXISTS (SELECT * FROM response WHERE id = 1 LIMIT 1) ")
    suspend fun responseIsExist(): Int

}