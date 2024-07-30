package com.example.paysky_crud_assement.domain.di

import com.example.paysky_crud_assement.data.repository.CRUDRepositoryImpl
import com.example.paysky_crud_assement.domain.repository.CRUDRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideUsersRepository(
        impl: CRUDRepositoryImpl
    ): CRUDRepository
}