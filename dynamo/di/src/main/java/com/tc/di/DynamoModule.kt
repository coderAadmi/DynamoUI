package com.tc.di

import android.content.Context
import androidx.room.Room
import com.tc.data.DynamoRepositoryImpl
import com.tc.data.db.DynamoDb
import com.tc.data.db.FormDao

import com.tc.data.network.DynamoApi
import com.tc.domain.repo.DynamoRepository
import com.tc.domain.usecases.GetAllFormsUSeCase
import com.tc.domain.usecases.GetFormByIdUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DynamoModule {

    const val BASE_URL = "http://172.168.21.71:8080/"

    @Provides
    @Singleton
    fun provideGetFormByIdUseCase(repository: DynamoRepository) : GetFormByIdUseCase{
        return GetFormByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFormsUseCase(repository: DynamoRepository) : GetAllFormsUSeCase{
        return GetAllFormsUSeCase(repository)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create() )
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkApi(retrofit: Retrofit ) : DynamoApi{
        return retrofit.create<DynamoApi>()
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) : DynamoDb{
        return  Room
            .databaseBuilder<DynamoDb>(context, name = "dynamo")
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideFormDao(db : DynamoDb) : FormDao{
        return db.getFormDao()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DynamoRepoInjector{
    @Binds
    abstract fun bindRepo(dynamoRepositoryImpl: DynamoRepositoryImpl) : DynamoRepository
}