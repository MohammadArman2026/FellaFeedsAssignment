package com.arman.dev.fellafeedsassignment.di

import com.arman.dev.fellafeedsassignment.feature.login.data.remote.AuthApiService
import com.arman.dev.fellafeedsassignment.feature.login.domain.repository.AuthRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    val json = Json{
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("http://localhost:8000/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    // we can add interceptor from here with that we can easily pass the header and auth
    // token but for simplicity lets leave it to the api service header.

    /**
     * auth api service being injected in the auth repository.
     */
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService{
        return retrofit.create<AuthApiService>(AuthApiService::class.java)
    }



}

//json.asConverterFactory("application/json".toMediaType())