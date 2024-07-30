package com.example.paysky_crud_assement.domain.di

import android.app.Application
import com.example.paysky_crud_assement.data.local.PostDao
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import com.example.paysky_crud_assement.domain.usecases.CreateUseCase
import com.example.paysky_crud_assement.domain.usecases.DeleteUseCase
import com.example.paysky_crud_assement.domain.usecases.ReadUseCase
import com.example.paysky_crud_assement.domain.usecases.UpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCreateUseCase(
        repository: CRUDRepository,
        postDao: PostDao,
        application: Application
    ): CreateUseCase {
        return CreateUseCase(repository, postDao,application)
    }

    @Provides
    @Singleton
    fun provideReadUseCase(
        repository: CRUDRepository,
        postDao: PostDao,
        application: Application
    ): ReadUseCase {
        return ReadUseCase(repository, postDao,application)
    }

    @Provides
    @Singleton
    fun provideUpdateUseCase(
        repository: CRUDRepository, postDao: PostDao, application: Application
    ): UpdateUseCase {
        return UpdateUseCase(repository, postDao, application)
    }

    @Provides
    @Singleton
    fun provideDeleteUseCase(
        repository: CRUDRepository, postDao: PostDao, application: Application
    ): DeleteUseCase {
        return DeleteUseCase(repository, postDao,application)
    }
}
