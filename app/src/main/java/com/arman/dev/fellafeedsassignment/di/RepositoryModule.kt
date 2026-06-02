package com.arman.dev.fellafeedsassignment.di

import com.arman.dev.fellafeedsassignment.feature.login.data.remote.AuthApiService
import com.arman.dev.fellafeedsassignment.feature.login.data.repository.AuthRepositoryImpl
import com.arman.dev.fellafeedsassignment.feature.login.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository{
       return AuthRepositoryImpl(authApiService)
    }
}