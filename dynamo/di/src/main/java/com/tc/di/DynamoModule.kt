package com.tc.di

import com.tc.data.DynamoRepositoryImpl
import com.tc.data.NetworkApi
import com.tc.domain.repo.DynamoRepository
import com.tc.domain.usecases.GetAllFormsUSeCase
import com.tc.domain.usecases.GetFormByIdUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DynamoModule {

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
    fun provideNetworkApi() : NetworkApi{
        return NetworkApi()
    }

//    @Provides
//    @Singleton
//     fun provideDynamoRepo(api : String) : DynamoRepository{
//         return DynamoRepositoryImpl()
//     }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DynamoRepoInjector{
    @Binds
    abstract fun bindRepo(dynamoRepositoryImpl: DynamoRepositoryImpl) : DynamoRepository
}