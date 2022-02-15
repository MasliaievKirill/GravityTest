package com.masliaiev.gravitytest.di

import android.app.Application
import com.masliaiev.gravitytest.data.database.AppDatabase
import com.masliaiev.gravitytest.data.database.ResponseDao
import com.masliaiev.gravitytest.data.network.ApiFactory
import com.masliaiev.gravitytest.data.network.ApiService
import com.masliaiev.gravitytest.data.repository.ResponseRepositoryImpl
import com.masliaiev.gravitytest.domain.repository.ResponseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindResponseRepository(impl: ResponseRepositoryImpl): ResponseRepository

    companion object{

        @Provides
        fun provideResponseDao(application: Application): ResponseDao{
            return AppDatabase.getInstance(application).responseDao()
        }

        @Provides
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }

}