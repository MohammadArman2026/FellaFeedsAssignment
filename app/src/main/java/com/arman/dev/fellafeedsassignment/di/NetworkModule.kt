package com.arman.dev.fellafeedsassignment.di

import com.arman.dev.fellafeedsassignment.core.network.ApiKeyInterceptor
import com.arman.dev.fellafeedsassignment.core.utils.Utility
import com.arman.dev.fellafeedsassignment.feature.auth.data.remote.AuthenticationApiService
import com.arman.dev.fellafeedsassignment.feature.auth.data.repository.AuthenticationRepoImpl
import com.arman.dev.fellafeedsassignment.feature.auth.domain.repository.AuthenticationRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    val json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Utility.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    /**
     * there will be two retrofit instances since some apis need auth token
     * and some does not need so to serve this purpose we can have named retrofit.
     */

    /**
     * auth api service being injected in the auth repository.
     */

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                interceptor
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthenticationApiService{
        return  retrofit.create(AuthenticationApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(apiService: AuthenticationApiService): AuthenticationRepository{
        return AuthenticationRepoImpl(apiService)
    }
}
